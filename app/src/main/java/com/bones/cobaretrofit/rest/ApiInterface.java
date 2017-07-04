package com.bones.cobaretrofit.rest;

import com.bones.cobaretrofit.model.CreditResponse;
import com.bones.cobaretrofit.model.Genres;
import com.bones.cobaretrofit.model.Movie;
import com.bones.cobaretrofit.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lenovo ip on 31/05/2017.
 */

public interface ApiInterface {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey,@Query("region") String region);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/credits")
    Call<CreditResponse> getMovieCredits(@Path("id") int id,@Query("api_key") String apiKey);


}
