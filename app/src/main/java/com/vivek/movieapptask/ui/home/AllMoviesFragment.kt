package com.vivek.movieapptask.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.vivek.movieapptask.Constants
import com.vivek.movieapptask.R
import com.vivek.movieapptask.base.BaseFragment
import com.vivek.movieapptask.data.network.model.interfaces.Movie
import com.vivek.movieapptask.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_all_movies.*


class AllMoviesFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_all_movies
    override val viewModel: MoviesViewModel
        get() = ViewModelProvider(requireActivity())[MoviesViewModel::class.java]
    override val shimmer: ShimmerFrameLayout
        get() = shimmerLayout

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private var loading = true
    private var pastVisiblesItems: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private lateinit var allMoviesFragmentInteractionListener: AllMoviesFragmentInteractionListener
    private var type: Int = 0
    private var currentPage: Int = 1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AllMoviesFragmentInteractionListener)
            allMoviesFragmentInteractionListener = context
        else
            throw IllegalStateException("Host must implement AllMoviesFragmentInteractionListener")
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener { _, _ -> true }
        observeValues()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        moviesAdapter = MoviesAdapter(mutableListOf()) {
            allMoviesFragmentInteractionListener.openMovieDetailsPage(it)
        }
        gridLayoutManager = GridLayoutManager(requireContext(), 3)
        rvAllMovies.layoutManager = gridLayoutManager
        rvAllMovies.adapter = moviesAdapter
        addPagination()
    }

    private fun addPagination() {
        rvAllMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = gridLayoutManager.childCount
                    totalItemCount = gridLayoutManager.itemCount
                    pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false
                            callApi(currentPage++, type)
                            loading = true
                        }
                    }
                }
            }
        })
    }

    private fun observeValues() {

        viewModel.moviesListLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { moviesList ->
                moviesAdapter.addItem(moviesList)
            }
        })

        viewModel.moviesTypeLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.type = it
                callApi(currentPage++, it)
            }
        })
    }

    private fun callApi(currentPage: Int, it: Int) {
        when (it) {
            Constants.LATEST_MOVIE -> {
                tvMoviesType.text = tvMoviesType.context.getString(R.string.txt_latest_movies)
                viewModel.getLatestMovies(currentPage)
            }
            Constants.NOW_PLAYING_MOVIE -> {
                tvMoviesType.text = tvMoviesType.context.getString(R.string.txt_now_playing_movies)
                viewModel.getNowPlayingMovies(currentPage)
            }
            Constants.POPULAR_MOVIE -> {
                tvMoviesType.text = tvMoviesType.context.getString(R.string.txt_popular_movies)
                viewModel.getPopularMovies(currentPage)
            }
            Constants.TOP_RATED_MOVIE -> {
                tvMoviesType.text = tvMoviesType.context.getString(R.string.txt_top_rated_movies)
                viewModel.getTopRatedMovies(currentPage)
            }
            else -> {
                tvMoviesType.text = tvMoviesType.context.getString(R.string.txt_upcoming_movies)
                viewModel.getUpcomingMovies(currentPage)
            }
        }
    }

}

interface AllMoviesFragmentInteractionListener {
    fun openMovieDetailsPage(movie: Movie)
}