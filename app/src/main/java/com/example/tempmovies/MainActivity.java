package com.example.tempmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.tempmovies.model.DiscoverRoot;
import com.example.tempmovies.model.Movie;
import com.example.tempmovies.viewmodels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTitleTextView;
    private TextView mDescriptionTextView;
    private TextView mReleaseTextView;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleTextView = findViewById(R.id.tv_title);
        mDescriptionTextView = findViewById(R.id.tv_description);
        mReleaseTextView = findViewById(R.id.tv_release_date);

        MainActivityViewModel mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getPopularMoviesList().observe(this, new Observer<DiscoverRoot>() {
            @Override
            public void onChanged(DiscoverRoot discoverRoot) {
                if (discoverRoot != null){
                    List<Movie> movies = discoverRoot.getResults();
                    Movie movie = movies.get(0);
                    mTitleTextView.setText(movie.getTitle());
                    mDescriptionTextView.setText(movie.getDescription());
                    mReleaseTextView.setText(movie.getReleaseDate());
                    Log.d(TAG, "Results count: " + movies.size());
                } else {
                    Log.e(TAG, "Movie list is null");
                }


            }
        });

    }
}