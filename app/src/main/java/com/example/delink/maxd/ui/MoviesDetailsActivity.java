package com.example.delink.maxd.ui;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.delink.maxd.Constants;
import com.example.delink.maxd.R;
import com.example.delink.maxd.modal.Movies;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoviesDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.container) ViewPager mViewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.fab) FloatingActionButton fab;

    private ArrayList<Movies> mMovies = new ArrayList<>();
    int positionInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);

        ButterKnife.bind(this);

        mMovies = Parcels.unwrap(getIntent().getParcelableExtra("movies"));
        positionInt = getIntent().getIntExtra("position", 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mMovies.get(positionInt).getTitle());

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);

        fab.setOnClickListener(this);
    }

    public void saveMovieToFavourite() {
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_FAVOURITE);
        databaseReference.push().setValue(mMovies.get(positionInt));
    }

    @Override
    public void onClick(View v) {
        if (v == fab){
            saveMovieToFavourite();
            Snackbar.make(v, mMovies.get(positionInt).getTitle() + " Has been added to the Favourite", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return null;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1);

            switch (position){
                case 0:
                    Bundle bundle = new Bundle();
                    bundle.putString("title", mMovies.get(positionInt).getTitle());
                    bundle.putString("overView", mMovies.get(positionInt).getOverview());
                    bundle.putString("releaseDate", mMovies.get(positionInt).getRelease_date());
                    bundle.putString("backDrop", mMovies.get(positionInt).getBackdrop_path());
                    bundle.putDouble("rating", mMovies.get(positionInt).getVote_average());
                    MovieOverviewFragment tabItem0 = new MovieOverviewFragment();
                    tabItem0.setArguments(bundle);
                    return  tabItem0;
                case 1:
                    Bundle agrs = new Bundle();
                    agrs.putString("title", mMovies.get(positionInt).getTitle());
                    MovieCastFragment tabItem1 = new MovieCastFragment();
                    tabItem1.setArguments(agrs);
                    return tabItem1;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "OVERVIEW";
                case 1:
                    return "CAST";
            }
            return null;
        }
    }
}
