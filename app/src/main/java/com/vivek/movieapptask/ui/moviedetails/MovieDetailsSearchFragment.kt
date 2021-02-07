package com.vivek.movieapptask.ui.moviedetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.ShimmerFrameLayout
import com.vivek.movieapptask.Constants
import com.vivek.movieapptask.R
import com.vivek.movieapptask.base.BaseFragment
import com.vivek.movieapptask.data.network.model.interfaces.Movie
import com.vivek.movieapptask.viewmodels.MoviesViewModel
import com.vivek.movieapptask.viewmodels.SearchViewModel
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsSearchFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_movie_details
    override val viewModel: SearchViewModel
        get() = ViewModelProvider(requireActivity())[SearchViewModel::class.java]
    override val shimmer: ShimmerFrameLayout
        get() = shimmerLayout

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener { _, _ -> true }
        observeValues()
    }

    private fun observeValues() {
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { movie ->
                setMovieData(movie)
            }
        })
    }

    private fun setMovieData(movie: Movie) {
        tvMovieName.text = movie.originalTitle
        tvMovieDesCription.text = movie.overview
        tvRating.text = String.format("%f%s%d", movie.voteAverage, "/", 10)
        tvReleaseDate.text =
            String.format("%s %s", getString(R.string.txt_release_date), movie.releaseDate)
        val path = Constants.IMAGE_BASE_URL + movie.posterPath
        Glide.with(ivMoviePoster.context).load(path)
            .apply(RequestOptions.bitmapTransform(BlurTransformation())).into(ivMoviePoster)
        Glide.with(ivMoviePoster.context).load(path).into(ivMoviePosterImageThumbnail)
    }
}