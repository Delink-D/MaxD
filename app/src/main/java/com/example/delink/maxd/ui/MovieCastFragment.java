package com.example.delink.maxd.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.delink.maxd.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCastFragment extends Fragment {

    @Bind(R.id.cast_movie_title) TextView mMovieTitle;

    public MovieCastFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie_cast, container, false);
        ButterKnife.bind(this, v);

        String title = getArguments().getString("title");

        mMovieTitle.setText(title + " Casts");

        return v;
    }

}
