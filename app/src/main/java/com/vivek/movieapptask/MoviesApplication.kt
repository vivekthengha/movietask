package com.vivek.movieapptask

import android.app.Application
import com.vivek.movieapptask.data.network.MovieAppTaskApi

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MovieAppTaskApi.init(BuildConfig.ACCESS_TOKEN) {}
    }

}