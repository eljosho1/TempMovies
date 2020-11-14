package com.example.tempmovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tempmovies.model.Movie;
import com.example.tempmovies.webservice.TmdbRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    LiveData<List<Movie>> popularMoviesList;
    // TODO - dependency injection for this repo?
    TmdbRepository tmdbRepository = TmdbRepository.getInstance();


    public LiveData<List<Movie>> getPopularMoviesList() {
        popularMoviesList = tmdbRepository.getPopularMovies();
        return popularMoviesList;
    }
}
