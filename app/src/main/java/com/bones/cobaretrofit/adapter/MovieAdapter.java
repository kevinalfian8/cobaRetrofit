package com.bones.cobaretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bones.cobaretrofit.R;
import com.bones.cobaretrofit.activity.DetailActivity;
import com.bones.cobaretrofit.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo ip on 31/05/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private int rowLayout;
    private static Context context;
    private static int id;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;
        ImageView moviePoster;


        public MovieViewHolder(View v) {
            super(v);
            movieTitle = (TextView) v.findViewById(R.id.movieTitle);
            moviePoster = (ImageView) v.findViewById(R.id.imagePoster);


        }


    }

    public MovieAdapter(List<Movie> movies,  Context context) {
        this.movies = movies;
        //this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_movie, null);

        // create ViewHolder

        MovieViewHolder viewHolder = new MovieViewHolder(itemLayoutView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        id = movies.get(position).getId();
        holder.movieTitle.setText(movies.get(position).getTitle());
        Picasso.with(context).load(movies.get(position).getPosterPath()).placeholder(R.color.colorPrimary).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



}
