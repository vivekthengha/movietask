package com.vivek.movieapptask.viewmodels

import androidx.lifecycle.MutableLiveData
import com.vivek.movieapptask.base.BaseViewModel
import com.vivek.movieapptask.data.network.MovieAppTaskApi
import com.vivek.movieapptask.data.network.model.interfaces.Movie

class SearchViewModel : BaseViewModel() {

    val searchMoviesLiveData = MutableLiveData<List<Movie>>()
    val movieLiveData = MutableLiveData<Movie>()

    fun searchMovies(page: Int, language: String, query: String) {
        MovieAppTaskApi.instance.searchMovies(page, language, query, {
            error.value = it
        }, {
            it?.let { paginatedMovie ->
                searchMoviesLiveData.value = paginatedMovie.movies
            }
        })
    }
}