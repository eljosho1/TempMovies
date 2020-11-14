package com.example.tempmovies.webservice;

import com.example.tempmovies.model.DiscoverRoot;
import com.example.tempmovies.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbApi {
    @GET("discover/movie?sort_by=popularity.desc")
    Call<DiscoverRoot> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Movie> getMovie(@Path("id") String movieID, @Query("api_key") String apiKey);
}
