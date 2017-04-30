package com.example.delink.maxd.modal;
import org.parceler.Parcel;


@Parcel
public class Movies {
    private String title;
    private String overview;
    private String release_date;
    private int vote_count;
    private double vote_average;
    private String poster_path;
    private String backdrop_path;
    private double popularity;
//    String genres;

    public Movies() {}

    public Movies(String title, String overview, String release_date,
                  int vote_count, double vote_average, String poster_path,
                  String backdrop_path, double popularity) {
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.popularity = popularity;
//        this.genres = genres;

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
        return poster_path;
    }
    public String getBackdrop_path(){
        return backdrop_path;
    }
    public double getPopularity(){
        return popularity;
    }
//    public String getGenres(){
//        return genres;
//    }
}