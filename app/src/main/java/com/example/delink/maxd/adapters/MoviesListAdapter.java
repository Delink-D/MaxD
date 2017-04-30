package com.example.delink.maxd.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.delink.maxd.R;
import com.example.delink.maxd.modal.Movies;
//import com.example.delink.maxd.ui.MovieDatails;
import com.example.delink.maxd.ui.MoviesDetailsActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder> {

    private ArrayList<Movies> mMovies= new ArrayList<>();
    private Context mContext;

    public MoviesListAdapter(Context context,ArrayList<Movies>movies){
        mContext=context;
        mMovies = movies;
    }

    @Override
    public MoviesListAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_activity,parent, false);
        MoviesViewHolder moviesViewHolder = new MoviesViewHolder(view);
        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesListAdapter.MoviesViewHolder holder, int position) {
        holder.bindMovies(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.movie_poster) ImageView mImage;
        @Bind(R.id.movie_title) TextView mTitle;
        @Bind(R.id.movie_rating) TextView mRate;

        private Context mContext;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext=itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindMovies(Movies movies) {
            String imageURL = "https://image.tmdb.org/t/p/w500" + movies.getPoster_path();
            Picasso.with(mContext).load(imageURL).into(mImage);

            String title = movies.getTitle();

            mTitle.setText(limitLength(title, 16));
            mRate.setText("Rating: " + movies.getVote_average() + "/10");

        }

        public String limitLength(String str, int maxLength){
            if (str.length() <= maxLength){
                return str;
            }else

                return str.substring(0, maxLength-3) + "...";
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, MoviesDetailsActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("movies", Parcels.wrap(mMovies));
            mContext.startActivity(intent);
        }
    }
}
