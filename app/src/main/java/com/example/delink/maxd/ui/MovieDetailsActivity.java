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
import com.example.delink.maxd.Constants;
import com.example.delink.maxd.R;
import com.example.delink.maxd.modal.MoviesTopRated;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.squareup.picasso.Picasso.*;

public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = MovieDetailsActivity.class.getSimpleName();

    @Bind(R.id.over_view) TextView mOverView;
    @Bind(R.id.backdrop) ImageView mBackdrop;
    @Bind(R.id.details_img) ImageView mImage;
    @Bind(R.id.details_rate) TextView mRating;
    @Bind(R.id.details_date) TextView mReleaseDate;
    @Bind(R.id.fab) FloatingActionButton mFab;

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

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.intheaters));

        collapsingToolbarLayout.setTitle(mMovies.get(position).getTitle());

        String poster = mMovies.get(position).getBackdrop_path();

        Glide.with(this).load(poster).into(mBackdrop);
        Glide.with(this).load(mMovies.get(position).getPoster_path()).into(mImage);
        mOverView.setText(mMovies.get(position).getOverview());
        mRating.setText("Movie Rating: " + mMovies.get(position).getVote_average());
        mReleaseDate.setText("Release Date: " + mMovies.get(position).getRelease_date());

        mFab.setOnClickListener(this);
    }

    public void saveMovieToFavourite() {
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_FAVOURITE);
        databaseReference.push().setValue(mMovies.get(position));
    }

    @Override
    public void onClick(View v) {
        if (v == mFab){
            saveMovieToFavourite();
            Snackbar.make(v, "Movie Added to Favourite", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }
}
