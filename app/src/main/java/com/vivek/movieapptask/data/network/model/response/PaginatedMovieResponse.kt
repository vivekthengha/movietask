package com.vivek.movieapptask.data.network.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vivek.movieapptask.data.network.model.interfaces.PaginatedMovie

data class PaginatedMovieResponse(
    @Expose
    @SerializedName("page")
    val page: Int,
    @Expose
    @SerializedName("results")
    override val movies: List<MovieResponse>,
    @Expose
    @SerializedName("total_pages")
    val totalPages: Int,
    @Expose
    @SerializedName("total_results")
    val totalResults: Int
): PaginatedMovie