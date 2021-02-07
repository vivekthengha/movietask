package com.vivek.movieapptask.data.network.model.response

import com.vivek.movieapptask.data.network.model.interfaces.Movie

data class MoviesListWrapper(
    var type: Int,
    var list: MutableList<Movie>
)