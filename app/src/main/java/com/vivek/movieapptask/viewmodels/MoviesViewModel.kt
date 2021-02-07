package com.vivek.movieapptask.viewmodels

import androidx.lifecycle.MutableLiveData
import com.vivek.movieapptask.Constants
import com.vivek.movieapptask.base.BaseViewModel
import com.vivek.movieapptask.data.network.MovieAppTaskApi
import com.vivek.movieapptask.data.network.model.interfaces.Movie
import java.util.TreeMap

class MoviesViewModel : BaseViewModel(){

    private val moviesMap = TreeMap<Int, List<Movie>?>()
    val moviesMapLiveData = MutableLiveData<TreeMap<Int, List<Movie>?>>()
    val movieLiveData = MutableLiveData<Movie>()
    val moviesTypeLiveData = MutableLiveData<Int>()
    val forLiveData = MutableLiveData<Int>()
    val moviesListLiveData = MutableLiveData<List<Movie>>()

    fun getLatestMovies(page: Int){
        MovieAppTaskApi.instance.getLatestMovies(page, {
            error.value = it
        }, { paginatedMovie ->
            forLiveData.value?.let {
                if (it == Constants.FOR_ALL_MOVIES){
                    moviesListLiveData.value = paginatedMovie?.movies
                }else{
                    moviesMap[Constants.LATEST_MOVIE] = paginatedMovie?.movies
                    moviesMapLiveData.value = moviesMap
                }
            }
        })
    }

    fun getUpcomingMovies(page: Int){
        MovieAppTaskApi.instance.getUpcomingMovies(page, {
            error.value = it
        }, { paginatedMovie ->
            forLiveData.value?.let {
                if (it == Constants.FOR_ALL_MOVIES){
                    moviesListLiveData.value = paginatedMovie?.movies
                }else{
                    moviesMap[Constants.UPCOMING_MOVIE] = paginatedMovie?.movies
                    moviesMapLiveData.value = moviesMap
                }
            }
        })
    }

    fun getNowPlayingMovies(page: Int){
        MovieAppTaskApi.instance.getNowPlayingMovies(page, {
            error.value = it
        }, { paginatedMovie ->
            forLiveData.value?.let {
                if (it == Constants.FOR_ALL_MOVIES){
                    moviesListLiveData.value = paginatedMovie?.movies
                }else{
                    moviesMap[Constants.NOW_PLAYING_MOVIE] = paginatedMovie?.movies
                    moviesMapLiveData.value = moviesMap
                }
            }
        })
    }

    fun getTopRatedMovies(page: Int){
        MovieAppTaskApi.instance.getTopRatedMovies(page, {
            error.value = it
        }, { paginatedMovie ->
            forLiveData.value?.let {
                if (it == Constants.FOR_ALL_MOVIES){
                    moviesListLiveData.value = paginatedMovie?.movies
                }else{
                    moviesMap[Constants.TOP_RATED_MOVIE] = paginatedMovie?.movies
                    moviesMapLiveData.value = moviesMap
                }
            }
        })
    }

    fun getPopularMovies(page: Int){
        MovieAppTaskApi.instance.getPopularMovies(page, {
            error.value = it
        }, { paginatedMovie ->
            forLiveData.value?.let {
                if (it == Constants.FOR_ALL_MOVIES){
                    moviesListLiveData.value = paginatedMovie?.movies
                }else{
                    moviesMap[Constants.POPULAR_MOVIE] = paginatedMovie?.movies
                    moviesMapLiveData.value = moviesMap
                }
            }
        })
    }

}