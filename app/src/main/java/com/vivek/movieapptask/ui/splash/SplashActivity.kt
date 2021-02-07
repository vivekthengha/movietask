package com.vivek.movieapptask.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vivek.movieapptask.BuildConfig
import com.vivek.movieapptask.data.network.MovieAppTaskApi
import com.vivek.movieapptask.ui.home.MoviesActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MoviesActivity::class.java))
        finish()
    }

}