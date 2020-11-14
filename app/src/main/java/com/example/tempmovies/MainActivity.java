package com.example.tempmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleTextView = findViewById(R.id.tv_title);
        mDescriptionTextView = findViewById(R.id.tv_description);
        mReleaseTextView = findViewById(R.id.tv_release_date);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getPopularMoviesList().observe(this, new Observer<DiscoverRoot>() {
            @Override
            public void onChanged(DiscoverRoot discoverRoot) {
                if (discoverRoot != null){

                    List<Movie> movies = discoverRoot.getResults();
                    Movie movie = movies.get(0);
                    Log.d(TAG, "Results count: " + movies.size());
                    mTitleTextView.setText(movie.getTitle());
                    mDescriptionTextView.setText(movie.getDescription());
                    mReleaseTextView.setText(movie.getReleaseDate());
                    Log.d(TAG, "Updated UI - getPopularMoviesList");
                } else {
                    Log.e(TAG, "Movie list is null");
                }


            }
        });

    }

    public void getById(View view){
        EditText idEditText = findViewById(R.id.et_enterId);
        mainActivityViewModel.getMovieById(idEditText.getText().toString()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                if (movie != null){
                    mTitleTextView.setText(movie.getTitle());
                    mDescriptionTextView.setText(movie.getDescription());
                    mReleaseTextView.setText(movie.getReleaseDate());
                    Log.d(TAG, "Updated UI - getById");
                } else {
                    Log.e(TAG, "Movie is null");
                }
            }
        });
    }

    public void refresh(View view){
        Log.d(TAG, "refreshing...");
        mainActivityViewModel.refresh();
        Log.d(TAG, "refreshed...");
    }
}