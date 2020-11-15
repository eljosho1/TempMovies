package com.example.tempmovies.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.tempmovies.BasicApp;
import com.example.tempmovies.model.DiscoverRoot;
import com.example.tempmovies.model.Movie;
import com.example.tempmovies.webservice.TmdbRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    LiveData<DiscoverRoot> popularMoviesRoot;
    LiveData<Movie> movieById;
    MutableLiveData<String> idLiveData = new MutableLiveData<>();
    TmdbRepository tmdbRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        //tmdbRepository = TmdbRepository.getInstance();
        tmdbRepository = ((BasicApp) application).getRepository();

        popularMoviesRoot = tmdbRepository.getPopularMovies();

        //id query
        movieById = Transformations.switchMap(
                idLiveData,
                (Function<String, LiveData<Movie>>)  query -> {
                    if (!query.isEmpty()) {
                        return tmdbRepository.getMovieById(query);
                    } else {
                        return getMovieById();
                    }
                });
    }


    public LiveData<DiscoverRoot> getPopularMoviesList() {
        return popularMoviesRoot;
    }

    public LiveData<Movie> getMovieById(){
        return movieById;
    }

    public void setMovieIdQuery(String id){
        idLiveData.setValue(id);
    }

    public void refresh(){
        popularMoviesRoot = tmdbRepository.getPopularMovies();
    }
}
