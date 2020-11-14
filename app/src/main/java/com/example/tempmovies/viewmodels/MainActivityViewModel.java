package com.example.tempmovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tempmovies.model.DiscoverRoot;
import com.example.tempmovies.model.Movie;
import com.example.tempmovies.webservice.TmdbRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    LiveData<DiscoverRoot> popularMoviesRoot;
    // TODO - dependency injection for this repo?
    TmdbRepository tmdbRepository = TmdbRepository.getInstance();


    public LiveData<DiscoverRoot> getPopularMoviesList() {
        popularMoviesRoot = tmdbRepository.getPopularMovies();
        return popularMoviesRoot;
    }
}
