//package com.example.delink.maxd.ui;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.delink.maxd.R;
//import com.example.delink.maxd.modal.MoviesTopRated;
//import com.squareup.picasso.Picasso;
//
//import org.parceler.Parcels;
//
//import java.util.ArrayList;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
//public class MovieDatails extends AppCompatActivity {
//    public static final String TAG = MovieDatails.class.getSimpleName();
//    private ArrayList<MoviesTopRated> mMovies = new ArrayList<>();
//    int startingPosition;
//
//    private Context mContext;
//    @Bind(R.id.poster_bg) ImageView posterBg;
//    @Bind(R.id.movie_details_title) TextView movieTitle;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_movie_datail);
//
//        ButterKnife.bind(this);
//
//        mMovies = Parcels.unwrap(getIntent().getParcelableExtra("movies"));
//        startingPosition = getIntent().getIntExtra("position", 0);
//
//        Picasso.with(mContext).load(mMovies.get(startingPosition).getBackdrop_path()).into(posterBg);
//        movieTitle.setText(mMovies.get(startingPosition).getTitle());
////        getDetails();
//    }
//
////    public void getDetails(){
////
////
////    }
//}
