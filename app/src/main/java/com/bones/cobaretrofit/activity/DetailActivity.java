package com.bones.cobaretrofit.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bones.cobaretrofit.R;
import com.bones.cobaretrofit.model.Movie;
import com.bones.cobaretrofit.rest.ApiClient;
import com.bones.cobaretrofit.rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    ImageView backdrop,poster;
    TextView desc,tagline,releaseDate,score;
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        int id = i.getIntExtra("id",0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        backdrop = (ImageView) findViewById(R.id.backdropMovie);
        poster = (ImageView) findViewById(R.id.posterMovie);
        desc = (TextView) findViewById(R.id.overview);
        tagline = (TextView) findViewById(R.id.movieTagline);
        releaseDate = (TextView) findViewById(R.id.movieReleaseDate);
        score = (TextView) findViewById(R.id.movieScore);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorTransparent));




        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Movie> call = apiService.getMovieDetails(id,MainActivity.API_KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Picasso.with(DetailActivity.this)
                        .load(response.body().getBackdropPath())
                        .placeholder(R.color.colorPrimary)
                        .into(backdrop);
                Picasso.with(DetailActivity.this)
                        .load(response.body().getPosterPath())
                        .placeholder(R.color.colorPrimary)
                        .into(poster);
                //title.setText(response.body().getTitle());
                collapsingToolbarLayout.setTitle(response.body().getTitle());
                tagline.setText(response.body().getTagline());
                desc.setText(response.body().getOverview());
                releaseDate.setText(response.body().getReleaseDate());
                score.setText(""+response.body().getVoteAverage());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }
}
