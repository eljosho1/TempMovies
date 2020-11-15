package com.example.tempmovies;

import android.app.Application;

import com.example.tempmovies.webservice.TmdbRepository;
import com.example.tempmovies.webservice.TmdbServiceGenerator;

import retrofit2.Retrofit;

public class BasicApp extends Application {

    public TmdbRepository getRepository(){
        return TmdbRepository.getInstance(getService());
    }

    public Retrofit getService(){
        return TmdbServiceGenerator.getInstance();
    }
}
