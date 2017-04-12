package com.example.delink.maxd.service;

import com.example.delink.maxd.Constants;
import com.example.delink.maxd.modal.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieDb {
    // Method to generate a url to get alll the Toprated movies
    public static void findTopRated(Callback callback) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL_TOPRATED).newBuilder();
        urlBuilder.addQueryParameter(Constants.KEY, Constants.MOVIE_DB_API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    // Method to generate a url to get alll the latest movies
    public static void findPlaying(Callback callback) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL_PLAYING).newBuilder();
        urlBuilder.addQueryParameter(Constants.KEY, Constants.MOVIE_DB_API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    // Method to generate a url to get all the upcoming movies
    public static void findUpcoming(Callback callback) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL_UPCOMING).newBuilder();
        urlBuilder.addQueryParameter(Constants.KEY, Constants.MOVIE_DB_API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    // Method to generate a url to get alll the popular movies
    public static void findPopular(Callback callback) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL_POPULAR).newBuilder();
        urlBuilder.addQueryParameter(Constants.KEY, Constants.MOVIE_DB_API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    // Method to generate a url to get alll the popular movies
    public static void search(String search, Callback callback) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL_SEARCH).newBuilder();
        urlBuilder.addQueryParameter(Constants.KEY, Constants.MOVIE_DB_API_KEY);
        urlBuilder.addQueryParameter(Constants.QUERY, search);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Movies> processResults(Response response) {
        ArrayList<Movies> movies = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject moviedbJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = moviedbJSON.getJSONArray("results");
                for (int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject moviesJSON = resultsJSON.getJSONObject(i);

                    String title = moviesJSON.getString("title");
                    String overview = moviesJSON.getString("overview");
                    String release_date = moviesJSON.getString("release_date");
                    int vote_count = moviesJSON.getInt("vote_count");
                    double vote_average = moviesJSON.getDouble("vote_average");
                    String poster_path = moviesJSON.getString("poster_path");
                    String backdrop_path = moviesJSON.getString("backdrop_path");
                    double popularity = moviesJSON.getDouble("popularity");

                    Movies movie = new Movies(title, overview, release_date, vote_count,
                            vote_average, poster_path, backdrop_path, popularity);
                    movies.add(movie);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
