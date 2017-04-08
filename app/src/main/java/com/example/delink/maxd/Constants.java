package com.example.delink.maxd;

public class Constants {

    // API_KEY
    public static final String MOVIE_DB_API_KEY = BuildConfig.MOVIE_DB_API_KEY;

    // MOVIES
    public static final String BASE_URL_TOPRATED = "https://api.themoviedb.org/3/movie/top_rated"; // url to send request to get toprated movies
    public static final String BASE_URL_UPCOMING = "https://api.themoviedb.org/3/movie/upcoming"; // url to send request to get upcming movies
    public static final String BASE_URL_POPULAR = "https://api.themoviedb.org/3/movie/popular"; // url to send request to get popular movies
    public static final String BASE_URL_LATEST = "https://api.themoviedb.org/3/movie/latest"; // url to send request to get latest movies

    // TV-SERIES
    public static final String BASE_TV_URL_SERIES = "https://api.themoviedb.org/3/tv/top_rated"; // url to send request to get to rated tv series
    public static final String BASE_TV_URL_POPULAR = "https://api.themoviedb.org/3/tv/popular"; // url to send request to get to popular tv series

    public static  final String KEY = "api_key";

    public static final String FIREBASE_CHILD_FAVOURITE = "favourite";
}
