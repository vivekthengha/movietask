package com.vivek.movieapptask.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vivek.movieapptask.Constants
import com.vivek.movieapptask.R
import com.vivek.movieapptask.data.network.model.interfaces.Movie
import kotlinx.android.synthetic.main.row_movie.view.*

class MoviesAdapter(private val movieList: MutableList<Movie>, val onItemCLick: (Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivMoviePoster: AppCompatImageView = view.ivMoviePoster
        private lateinit var movie: Movie

        init {
            ivMoviePoster.setOnClickListener {
                onItemCLick(movie)
            }
        }

        fun bind(movie: Movie) {
            this.movie = movie
            val path = Constants.IMAGE_BASE_URL + movie.posterPath
            Glide.with(ivMoviePoster.context).load(path).into(ivMoviePoster)
        }
    }

    fun addItem(moviesList: List<Movie>){
        movieList.addAll(moviesList)
        notifyItemRangeInserted(movieList.size - 1, moviesList.size)
    }

    fun clear(){
        movieList.clear()
        notifyDataSetChanged()
    }
}