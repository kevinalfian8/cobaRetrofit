package com.bones.cobaretrofit.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bones.cobaretrofit.R;
import com.bones.cobaretrofit.adapter.MovieAdapter;
import com.bones.cobaretrofit.adapter.ViewPagerAdapter;
import com.bones.cobaretrofit.listener.ClickInterface;
import com.bones.cobaretrofit.listener.RecyclerTouchListener;
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

    public static final String API_KEY = "d43d08456676c6c1cacf66a3793741cb";
    List<Movie> movies;
    ViewPager pager;
    ViewPagerAdapter pagerAdapter;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Popular Movies");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));



        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);



        if(API_KEY.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please obtain your API key", Toast.LENGTH_LONG).show();
            return;
        }

        /*final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movies = response.body().getResults();

                recyclerView.setAdapter(new MovieAdapter(movies,MainActivity.this));
                Log.d(TAG,"Number of movies received: "+ movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickInterface() {
            @Override
            public void onClick(View view, int position) {
                int id = movies.get(position).getId();
                Intent i = new Intent(MainActivity.this,DetailActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/


    }


}
