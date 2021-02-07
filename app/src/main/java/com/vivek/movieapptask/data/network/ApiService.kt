package com.vivek.movieapptask.data.network

import com.vivek.movieapptask.data.network.model.response.PaginatedMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Call<PaginatedMovieResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Call<PaginatedMovieResponse>

    @GET("movie/latest")
    fun getLatestMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Call<PaginatedMovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Call<PaginatedMovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Call<PaginatedMovieResponse>

    @GET("search/movie")
    fun searchMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Call<PaginatedMovieResponse>
}