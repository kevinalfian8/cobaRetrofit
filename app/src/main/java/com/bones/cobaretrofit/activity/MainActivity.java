package com.bones.cobaretrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bones.cobaretrofit.R;
import com.bones.cobaretrofit.adapter.MovieAdapter;
import com.bones.cobaretrofit.model.Movie;
import com.bones.cobaretrofit.model.MovieResponse;
import com.bones.cobaretrofit.rest.ApiClient;
import com.bones.cobaretrofit.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private static final String API_KEY = "d43d08456676c6c1cacf66a3793741cb";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(API_KEY.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please obtain your API key", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();

                recyclerView.setAdapter(new MovieAdapter(movies,MainActivity.this));
                Log.d(TAG,"Number of movies received: "+ movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });

    }
}
