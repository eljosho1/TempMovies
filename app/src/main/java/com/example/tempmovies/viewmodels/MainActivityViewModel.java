package com.example.tempmovies.viewmodels;

import android.app.Application;
import android.util.Log;

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

    private static String TAG = "MainActivityViewModel";
    LiveData<DiscoverRoot> popularMoviesRoot;
    LiveData<Movie> movieById;
    MutableLiveData<String> idLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> refreshTrigger = new MutableLiveData<>();
    TmdbRepository tmdbRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        //tmdbRepository = TmdbRepository.getInstance();
        tmdbRepository = ((BasicApp) application).getRepository();

        //popularMoviesRoot = tmdbRepository.getPopularMovies();
        //popular movies request with refresh
        popularMoviesRoot = Transformations.switchMap(
                refreshTrigger,
                (Function<Boolean, LiveData<DiscoverRoot>>)  query -> {

                    return tmdbRepository.getPopularMovies();
                });
        refresh();

        //id query
        movieById = Transformations.switchMap(
                idLiveData,
                (Function<String, LiveData<Movie>>)  query -> {
                    if (!query.isEmpty()) {
                        Log.d(TAG, "query - query");
                        return tmdbRepository.getMovieById(query);
                    } else {
                        Log.d(TAG, "query is nothing");
                        return tmdbRepository.getMovieById("10420");
//                        return getMovieById();
                    }
                });
    }


    public LiveData<DiscoverRoot> getPopularMoviesList() {
        return popularMoviesRoot;
    }

    public LiveData<Movie> getMovieById(){
        Log.d(TAG, "movieById returning " + movieById);
        return movieById;
    }

    public void setMovieIdQuery(String id){
        idLiveData.setValue(id);
    }

    public void refresh(){
        //unsure if this is the right way to do it...
        refreshTrigger.setValue(true);
    }
}
