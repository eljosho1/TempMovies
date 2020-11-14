package com.example.tempmovies.webservice;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tempmovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TmdbRepository {

    private final String TAG = "TmdbRepository";
    private static TmdbRepository tmdbRepository;

    //TODO - this instantiaites its own webservice. should use dependency injection instead
    private TmdbApi tmdbService = TmdbServiceGenerator.createService(TmdbApi.class);

    private static String apiKey = "58d008aa8bc2b7b6bcff70408b9f82cc";

    public static TmdbRepository getInstance(){
        if (tmdbRepository == null){
            tmdbRepository = new TmdbRepository();
        }
        return tmdbRepository;
    }

    public LiveData<List<Movie>> getPopularMovies(){
        Log.d(TAG, "calling getPopularMovies");
        MutableLiveData<List<Movie>> movies = new MutableLiveData<List<Movie>>();
        tmdbService.getPopularMovies(apiKey).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){
                    movies.setValue(response.body());
                    Log.d(TAG, "response success");
                } else {
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                movies.setValue(null);
                Log.e(TAG, t.getMessage());
            }
        });
        return movies;
    }

    //TODO - implement getMovieById()
}
