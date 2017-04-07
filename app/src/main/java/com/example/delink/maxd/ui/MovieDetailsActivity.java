package com.example.delink.maxd.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.example.delink.maxd.R;
import com.example.delink.maxd.modal.MoviesTopRated;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.squareup.picasso.Picasso.*;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String TAG = MovieDetailsActivity.class.getSimpleName();

    @Bind(R.id.over_view)
    TextView overView;
    @Bind(R.id.backdrop) ImageView mImage;

    private ArrayList<MoviesTopRated> mMovies = new ArrayList<>();
    int position;

    private Context mContext;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        mMovies = Parcels.unwrap(getIntent().getParcelableExtra("movies"));
        position = getIntent().getIntExtra("position", 0);

        CollapsingToolbarLayout x = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        x.setBackgroundDrawable(getResources().getDrawable(R.drawable.intheaters));

        x.setTitle(mMovies.get(position).getTitle());

        String poster = mMovies.get(position).getPoster_path();

//        mImage.setBackground(new Glide(with(this).load(poster)));
//        mImage.setBackground(Glide.with(this).load(poster).);
        //Glide.with(this).load(mMovies.get(position).getBackdrop_path()).into((ImageView) findViewById(R.id.backdrop));
        Glide.with(this).load(poster).into(mImage);


        overView.setText(mMovies.get(position).getOverview());
//        Picasso.with(mContext).load(mMovies.get(position).getBackdrop_path()).into(x.setBackground(););


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
