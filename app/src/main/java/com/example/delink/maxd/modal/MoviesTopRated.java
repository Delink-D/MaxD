package com.example.delink.maxd.modal;
import org.parceler.Parcel;

@Parcel
public class MoviesTopRated {
    private String title;
    private String overview;
    private String release_date;
    private int vote_count;
    private double vote_average;
    private String poster_path;
    private String backdrop_path;
    private double popularity;

    public MoviesTopRated() {}

    public MoviesTopRated(String title,String overview,String release_date,
                          int vote_count,double vote_average,String poster_path,
                          String backdrop_path, double popularity) {
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.popularity = popularity;

    }

    public String getTitle(){
        return title;
    }
    public String getOverview(){
        return  overview;
    }
    public String getRelease_date(){
        return  release_date;
    }
    public int getVote_count(){
        return vote_count;
    }
    public double getVote_average(){
        return vote_average;
    }
    public String getPoster_path(){
        return "https://image.tmdb.org/t/p/w500" + poster_path;
    }
    public String getBackdrop_path(){
        return "https://image.tmdb.org/t/p/w1000" + backdrop_path;
    }
    public double getPopularity(){
        return popularity;
    }
}