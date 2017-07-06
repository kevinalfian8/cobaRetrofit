package com.bones.cobaretrofit.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bones.cobaretrofit.R;
import com.bones.cobaretrofit.activity.DetailActivity;
import com.bones.cobaretrofit.activity.MainActivity;
import com.bones.cobaretrofit.adapter.MovieAdapter;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {


    public PopularFragment() {
        // Required empty public constructor
    }

    List<Movie> movies;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.movies_popular);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getPopularMovies(MainActivity.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movies = response.body().getResults();

                recyclerView.setAdapter(new MovieAdapter(movies,getActivity()));
                Log.d(MainActivity.TAG,"Number of movies received: "+ movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(MainActivity.TAG, t.toString());
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickInterface() {
            @Override
            public void onClick(View view, int position) {
                int id = movies.get(position).getId();
                Intent i = new Intent(getActivity(),DetailActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }


}


