package com.example.tempmovies.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tempmovies.BasicApp;
import com.example.tempmovies.model.DiscoverRoot;
import com.example.tempmovies.model.Movie;
import com.example.tempmovies.webservice.TmdbRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    MutableLiveData<DiscoverRoot> popularMoviesRoot;
    MutableLiveData<Movie> movieById;
    // TODO - dependency injection for this repo?
    // shold View be sending the repo to viewmodel?
    TmdbRepository tmdbRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        //tmdbRepository = TmdbRepository.getInstance();
        tmdbRepository = ((BasicApp) application).getRepository();
        popularMoviesRoot = tmdbRepository.getPopularMovies();
    }


    public LiveData<DiscoverRoot> getPopularMoviesList() {
        return popularMoviesRoot;
    }

    public LiveData<Movie> getMovieById(String id){
        movieById = tmdbRepository.getMovieById(id);
        return movieById;
    }

    public void refresh(){
        popularMoviesRoot = tmdbRepository.getPopularMovies();
    }
}
