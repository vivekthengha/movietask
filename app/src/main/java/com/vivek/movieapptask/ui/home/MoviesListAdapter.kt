package com.vivek.movieapptask.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vivek.movieapptask.Constants
import com.vivek.movieapptask.R
import com.vivek.movieapptask.data.network.model.interfaces.Movie
import com.vivek.movieapptask.data.network.model.response.MoviesListWrapper
import kotlinx.android.synthetic.main.row_movies_list.view.*

class MoviesListAdapter(private val moviesWrapperList: MutableList<MoviesListWrapper>, private val onItemClick: (Movie?, View, Int) -> Unit): RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        return MoviesListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_movies_list, parent, false))
    }

    override fun getItemCount(): Int {
        return moviesWrapperList.size
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        holder.bind(moviesWrapperList[position])
    }

    inner class MoviesListViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val rvMovies = view.rvMovies
        private val tvMoviesType = view.tvMoviesType
        private val tvViewAll = view.tvViewAll

        fun bind(moviesListWrapper: MoviesListWrapper){

            rvMovies.layoutManager = LinearLayoutManager(rvMovies.context, LinearLayoutManager.HORIZONTAL, false)
            rvMovies.setHasFixedSize(true)
            rvMovies.adapter = MoviesAdapter(moviesListWrapper.list) {
                onItemClick(it, rvMovies, moviesListWrapper.type)
            }

            tvViewAll.setOnClickListener {
                onItemClick(null, tvViewAll, moviesListWrapper.type)
            }

            when (moviesListWrapper.type) {
                Constants.LATEST_MOVIE -> {
                    tvMoviesType.text = tvMoviesType.context.getString(R.string.txt_latest_movies)
                }
                Constants.NOW_PLAYING_MOVIE -> {
                    tvMoviesType.text = tvMoviesType.context.getString(R.string.txt_now_playing_movies)
                }
                Constants.POPULAR_MOVIE -> {
                    tvMoviesType.text = tvMoviesType.context.getString(R.string.txt_popular_movies)
                }
                Constants.TOP_RATED_MOVIE -> {
                    tvMoviesType.text = tvMoviesType.context.getString(R.string.txt_top_rated_movies)
                }
                else -> {
                    tvMoviesType.text = tvMoviesType.context.getString(R.string.txt_upcoming_movies)
                }
            }
        }
    }

    fun addAll(moviesListWrappers: List<MoviesListWrapper>){
        moviesWrapperList.clear()
        moviesWrapperList.addAll(moviesListWrappers)
        notifyDataSetChanged()
    }
}