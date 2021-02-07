package com.vivek.movieapptask.data.network.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vivek.movieapptask.data.network.model.interfaces.Movie

data class MovieResponse(
    @Expose
    @SerializedName("adult")
    override val adult: Boolean,
    @Expose
    @SerializedName("backdrop_path")
    override val backdropPath: String,
    @Expose
    @SerializedName("genre_ids")
    val genre_ids: List<Int>,
    @Expose
    @SerializedName("id")
    override val id: Int,
    @Expose
    @SerializedName("original_language")
    override val originalLanguage: String,
    @Expose
    @SerializedName("original_title")
    override val originalTitle: String,
    @Expose
    @SerializedName("overview")
    override val overview: String,
    @Expose
    @SerializedName("popularity")
    override val popularity: Double,
    @Expose
    @SerializedName("poster_path")
    override val posterPath: String,
    @Expose
    @SerializedName("release_date")
    override val releaseDate: String,
    @Expose
    @SerializedName("title")
    override val title: String,
    @Expose
    @SerializedName("video")
    override val video: Boolean,
    @Expose
    @SerializedName("vote_average")
    override val voteAverage: Double,
    @Expose
    @SerializedName("vote_count")
    override val voteCount: Int
): Movie {
    override val genres: String
        get() = ""
}