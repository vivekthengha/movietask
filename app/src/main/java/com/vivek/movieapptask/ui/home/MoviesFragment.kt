package com.vivek.movieapptask.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.vivek.movieapptask.R
import com.vivek.movieapptask.base.BaseFragment
import com.vivek.movieapptask.data.network.model.interfaces.Movie
import com.vivek.movieapptask.data.network.model.response.MoviesListWrapper
import com.vivek.movieapptask.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.lang.IllegalStateException

class MoviesFragment: BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_home
    override val viewModel: MoviesViewModel
        get() = ViewModelProvider(requireActivity())[MoviesViewModel::class.java]
    override val shimmer: ShimmerFrameLayout
        get() = shimmerLayout

    private lateinit var moviesListAdapter: MoviesListAdapter
    private val moviesWrapperList = mutableListOf<MoviesListWrapper>()
    private var firstPage: Int = 1

    private lateinit var homeFragmentInteractionListener: MoviesFragmentInteractionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MoviesFragmentInteractionListener)
            homeFragmentInteractionListener = context
        else throw IllegalStateException("Host must implement HomeFragmentInteractionListener.")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        setUpRecyclerView()
        observeValues()
        callApis()
    }

    private fun callApis() {
        viewModel.getLatestMovies(firstPage)
        viewModel.getNowPlayingMovies(firstPage)
        viewModel.getPopularMovies(firstPage)
        viewModel.getTopRatedMovies(firstPage)
        viewModel.getUpcomingMovies(firstPage)
    }

    private fun observeValues() {
        viewModel.moviesMapLiveData.observe(viewLifecycleOwner, Observer { moviesMap ->
            moviesWrapperList.clear()
            moviesMap.forEach {entry ->
                entry.value?.let {
                    val moviesListWrapper = MoviesListWrapper(entry.key, it.toMutableList())
                    moviesWrapperList.add(moviesListWrapper)
                }
            }
            moviesListAdapter.addAll(moviesWrapperList)
        })
    }

    private fun setUpRecyclerView() {
        moviesListAdapter = MoviesListAdapter(mutableListOf()) { movie, view, type ->
            when(view.id){
                R.id.tvViewAll -> {
                    homeFragmentInteractionListener.viewAllMovies(type)
                }

                R.id.rvMovies -> {
                    movie?.let {
                        homeFragmentInteractionListener.openMovieDetailsPage(movie)
                    }
                }
            }
        }
        rvMovies.layoutManager = LinearLayoutManager(requireContext())
        rvMovies.adapter = moviesListAdapter
    }
}

interface MoviesFragmentInteractionListener{
    fun viewAllMovies(type: Int)
    fun openMovieDetailsPage(movie: Movie)
}