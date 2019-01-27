package br.com.fabricioandrade.filmesmvp.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.fabricioandrade.filmesmvp.BuildConfig
import br.com.fabricioandrade.filmesmvp.R
import br.com.fabricioandrade.filmesmvp.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_item_movie.view.*
import java.util.*

class MoviesAdapter(private val itemMovieClickedListener: ItemMovieClickedListener?): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_item_movie, parent, false))
    }

    private var movies: List<Movie> = ArrayList()

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        Picasso.get().load(BuildConfig.BASE_URL_IMAGE + movie.posterPath).into(holder.img)
        holder?.img?.setOnClickListener{
            itemMovieClickedListener?.onClick(movie)
        }
    }

    fun setData(data: List<Movie>) {
        movies = data
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder (view : View) :RecyclerView.ViewHolder(view){
        val img : ImageView = view.movie_poster
        val title : TextView = view.title
    }

    interface ItemMovieClickedListener {
        fun onClick(movie: Movie)
    }
}

