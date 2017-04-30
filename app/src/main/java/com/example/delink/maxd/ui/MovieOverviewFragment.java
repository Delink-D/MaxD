package com.example.delink.maxd.ui;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.delink.maxd.Constants;
import com.example.delink.maxd.R;
import com.example.delink.maxd.modal.Movies;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieOverviewFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = MovieOverviewFragment.class.getSimpleName();

    @Bind(R.id.movie_detail_title) TextView mMovieTitle;
    @Bind(R.id.movie_category) TextView mMovieCategory;
    @Bind(R.id.movieOverView) TextView mMovieOverview;
    @Bind(R.id.dateRelease) TextView mMovieReleaseDate;
    @Bind(R.id.movierating) TextView mMovieRating;
    @Bind(R.id.details_backdrop) ImageView mBackDrop;

    public MovieOverviewFragment () {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_overview, container, false);
        ButterKnife.bind(this, view);

        String title = getArguments().getString("title");
        String overView = getArguments().getString("overView");
        String releaseDate = getArguments().getString("releaseDate");
        String backDrop = getArguments().getString("backDrop");
        Double rating = getArguments().getDouble("rating");

        mMovieTitle.setText(title);
        mMovieOverview.setText(overView);
        mMovieReleaseDate.setText(String.format(getString(R.string.released_date), releaseDate));
        mMovieRating.setText(getString(R.string.rating) + rating);
        mMovieCategory.setText("Category: Science Fiction");

        Picasso.with(getActivity())
                .load("https://image.tmdb.org/t/p/w1000" + backDrop)
                .into(new Target() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mBackDrop.setBackground(new BitmapDrawable(getContext().getResources(), bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
