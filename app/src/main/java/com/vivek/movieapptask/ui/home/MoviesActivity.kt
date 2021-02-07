package com.vivek.movieapptask.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.vivek.movieapptask.Constants
import com.vivek.movieapptask.R
import com.vivek.movieapptask.base.BaseActivity
import com.vivek.movieapptask.data.network.model.interfaces.Movie
import com.vivek.movieapptask.ui.moviedetails.MovieDetailsFragment
import com.vivek.movieapptask.ui.search.SearchActivity
import com.vivek.movieapptask.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.activity_home.*

class MoviesActivity : BaseActivity(), MoviesFragmentInteractionListener,
    AllMoviesFragmentInteractionListener {
    override val layoutId: Int
        get() = R.layout.activity_home
    override val viewModel: MoviesViewModel
        get() = ViewModelProvider(this)[MoviesViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setMoviesFragment()
    }

    private fun setMoviesFragment() {
        viewModel.forLiveData.value = Constants.FOR_HOME
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, MoviesFragment()).commit()
    }

    override fun viewAllMovies(type: Int) {
        viewModel.moviesTypeLiveData.value = type
        viewModel.forLiveData.value = Constants.FOR_ALL_MOVIES
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, AllMoviesFragment()).addToBackStack(null).commit()
    }

    override fun openMovieDetailsPage(movie: Movie) {
        viewModel.movieLiveData.value = movie
        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer, MovieDetailsFragment()).addToBackStack(null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_movies, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search) {
            startActivity(Intent(this, SearchActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}