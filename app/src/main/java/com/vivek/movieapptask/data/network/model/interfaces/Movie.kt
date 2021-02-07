package com.vivek.movieapptask.data.network.model.interfaces

interface Movie {
    val adult: Boolean
    val backdropPath: String
    val genres: String
    val id: Int
    val originalLanguage: String
    val originalTitle: String
    val overview: String
    val popularity: Double
    val posterPath: String
    val releaseDate: String
    val title: String
    val video: Boolean
    val voteAverage: Double
    val voteCount: Int
}