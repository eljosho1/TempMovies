package com.example.tempmovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tempmovies.model.DiscoverRoot;
import com.example.tempmovies.model.Movie;
import com.example.tempmovies.webservice.TmdbRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    MutableLiveData<DiscoverRoot> popularMoviesRoot;
    MutableLiveData<Movie> movieById;
    // TODO - dependency injection for this repo?
    // shold View be sending the repo to viewmodel?
    TmdbRepository tmdbRepository = TmdbRepository.getInstance();


    public LiveData<DiscoverRoot> getPopularMoviesList() {
        popularMoviesRoot = tmdbRepository.getPopularMovies();
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
