package com.bones.cobaretrofit.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.bones.cobaretrofit.R;
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

public class SearchActivity extends AppCompatActivity {


    RecyclerView list;
    EditText query;
    ImageButton search;
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        list = (RecyclerView) findViewById(R.id.resultsList);
        list.setHasFixedSize(true);
        list.setLayoutManager(new GridLayoutManager(this,2));

        query = (EditText) findViewById(R.id.txtSearch);
        search = (ImageButton) findViewById(R.id.btnSearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movies != null){
                    movies.clear();
                }
                searchData(query.getText().toString());
            }
        });

        list.addOnItemTouchListener(new RecyclerTouchListener(SearchActivity.this, list, new ClickInterface() {
            @Override
            public void onClick(View view, int position) {
                int id = movies.get(position).getId();
                Intent i = new Intent(SearchActivity.this,DetailActivity.class);
                i.putExtra("id",id);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }

    public void searchData(String title){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getSearchResults(MainActivity.API_KEY,title);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movies = response.body().getResults();

                list.setAdapter(new MovieAdapter(movies,SearchActivity.this));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Sum ting wong", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
