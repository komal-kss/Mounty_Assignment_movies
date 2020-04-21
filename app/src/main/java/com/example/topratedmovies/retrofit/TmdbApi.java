package com.example.topratedmovies.retrofit;

import com.example.topratedmovies.pojo.TopRatedMoviesResponse;
import com.example.topratedmovies.pojo.UpcomingMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbApi {

    //https://api.themoviedb.org/3/movie/top_rated?api_key=2d3e3995bfdaf5c190e7abac26e49040
    @GET("movie/top_rated")
    Call<TopRatedMoviesResponse> getTopRatedMovies(
            @Query("api_key") String apiKey
    );

    //https://api.themoviedb.org/3/movie/upcoming?api_key=2d3e3995bfdaf5c190e7abac26e49040
    @GET("movie/upcoming")
    Call<UpcomingMoviesResponse> getUpcomingMovies(
            @Query("api_key") String apiKey
    );


}
