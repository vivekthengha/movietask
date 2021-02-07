package com.vivek.movieapptask.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.vivek.movieapptask.Constants
import com.vivek.movieapptask.R
import com.vivek.movieapptask.base.BaseActivity
import com.vivek.movieapptask.ui.home.MoviesAdapter
import com.vivek.movieapptask.ui.moviedetails.MovieDetailsFragment
import com.vivek.movieapptask.ui.moviedetails.MovieDetailsSearchFragment
import com.vivek.movieapptask.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.flexbox_chips.view.*

class SearchActivity : BaseActivity(), TextWatcher {

    override val layoutId: Int
        get() = R.layout.activity_search
    override val viewModel: SearchViewModel
        get() = ViewModelProvider(this)[SearchViewModel::class.java]

    private var classification: Int = Constants.HOLLYWOOD
    private var clChipBollyWood: FrameLayout? = null
    private var clChipHollyWood: FrameLayout? = null
    private var currentPage: Int = 1;
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpRecyclerView()
        observeValues()
        etSearch.addTextChangedListener(this)
        ivClear.setOnClickListener {
            etSearch.setText("")
            moviesAdapter.clear()
        }
    }

    private fun observeValues() {
        moviesAdapter.clear()
        viewModel.searchMoviesLiveData.observe(this, Observer {
            it?.let { moviesList ->
                moviesAdapter.addItem(moviesList)
            }
        })
    }

    private fun setUpRecyclerView() {
        moviesAdapter = MoviesAdapter(mutableListOf()) {
            viewModel.movieLiveData.value = it
            supportFragmentManager.beginTransaction()
                .replace(R.id.flContainer, MovieDetailsSearchFragment()).addToBackStack(null)
                .commit()
        }
        gridLayoutManager = GridLayoutManager(this, 3)
        rvMovie.layoutManager = gridLayoutManager
        rvMovie.adapter = moviesAdapter
    }

    private fun searchMovies(query: String) {
        if (classification == Constants.HOLLYWOOD) {
            viewModel.searchMovies(currentPage, "en-US", query)
        } else {
            viewModel.searchMovies(currentPage, "hi-IN", query)
        }
    }

    override fun afterTextChanged(s: Editable?) {
        s?.let { editable ->
            if (editable.toString().isEmpty()) {
                moviesAdapter.clear()
            } else {
                moviesAdapter.clear()
                searchMovies(editable.toString())
                ivClear.visibility = View.GONE
            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}