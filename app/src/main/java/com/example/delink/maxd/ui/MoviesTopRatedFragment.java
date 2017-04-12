package com.example.delink.maxd.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.delink.maxd.R;
import com.example.delink.maxd.adapters.MoviesListAdapter;
import com.example.delink.maxd.modal.Movies;
import com.example.delink.maxd.service.MovieDb;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesTopRatedFragment extends Fragment {

    public static final String TAG = MainActivity.class.getSimpleName();
    //    call recyclerView from the
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    //    private  adapter;
    private MoviesListAdapter mAdapter;

    //    Array list from the modal class
    public ArrayList<Movies> mTopRated = new ArrayList<>();

    public MoviesTopRatedFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_top_rated, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

//    // Get top rated movies
    private void getTopRated() {
        final MovieDb movieDbService = new MovieDb();
        movieDbService.findTopRated(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mTopRated = movieDbService.processResults(response);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new MoviesListAdapter(getContext(), mTopRated);
                        mRecyclerView.setAdapter(mAdapter);

                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }

                });
            }
        });
    }
}


