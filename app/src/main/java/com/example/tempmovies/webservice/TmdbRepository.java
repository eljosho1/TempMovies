package com.example.tempmovies.webservice;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tempmovies.model.DiscoverRoot;
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

    public LiveData<DiscoverRoot> getPopularMovies(){
        Log.d(TAG, "calling getPopularMovies");
        MutableLiveData<DiscoverRoot> root = new MutableLiveData<DiscoverRoot>();
        tmdbService.getPopularMovies(apiKey).enqueue(new Callback<DiscoverRoot>() {
            @Override
            public void onResponse(Call<DiscoverRoot> call, Response<DiscoverRoot> response) {
                if (response.isSuccessful()){
                    root.setValue(response.body());
                    Log.d(TAG, "response success");
                } else {
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<DiscoverRoot> call, Throwable t) {
                root.setValue(null);
                Log.e(TAG, t.getMessage());
            }
        });
        return root;
    }

    //TODO - implement getMovieById()
}
