package com.example.fractalmedia.services;

import com.example.fractalmedia.model.Movie;
import com.example.fractalmedia.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBService {

    @GET("movie/popular")
    Call<MoviesResponse> listPopularMovie(@Query("api_key") String api_key);

    @GET("movie/{id}")
    Call<Movie> getMovieDetail(@Path("id") String id, @Query("api_key") String api_key);

}
