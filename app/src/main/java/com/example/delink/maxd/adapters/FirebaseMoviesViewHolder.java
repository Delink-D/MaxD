package com.example.delink.maxd.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.delink.maxd.Constants;
import com.example.delink.maxd.R;
import com.example.delink.maxd.modal.Movies;
import com.example.delink.maxd.ui.MoviesDetailsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private View mView;
    private Context mContext;

    public FirebaseMoviesViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindMovies(Movies moviesTopRated) {
        ImageView mImage = (ImageView) mView.findViewById(R.id.movie_poster);
        TextView mTitle = (TextView) mView.findViewById(R.id.movie_title);
        TextView mRate = (TextView) mView.findViewById(R.id.movie_rating);

        String imageURL = "https://image.tmdb.org/t/p/w500" + moviesTopRated.getPoster_path();

        Picasso.with(mContext).load(imageURL).into(mImage);

        String title = moviesTopRated.getTitle();

        mTitle.setText(limitLength(title, 16));
        mRate.setText("Rating: " + moviesTopRated.getVote_average() + "/10");
    }

    public String limitLength(String str, int maxLength){
        if (str.length() <= maxLength){
            return str;
        }else

            return str.substring(0, maxLength-3) + "...";
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Movies> movies = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_FAVOURITE);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    movies.add(snapshot.getValue(Movies.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MoviesDetailsActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("movies", Parcels.wrap(movies));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
