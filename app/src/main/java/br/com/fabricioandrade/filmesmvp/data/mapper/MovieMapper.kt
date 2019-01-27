package br.com.fabricioandrade.filmesmvp.data.mapper

import br.com.fabricioandrade.filmesmvp.data.network.response.MovieResponse
import br.com.fabricioandrade.filmesmvp.model.Movie
import java.util.*

class MovieMapper {

    fun responseToMovie(moviesResponse: List<MovieResponse>): List<Movie> {
        val movies = ArrayList<Movie>()

        for (moviesResponse in moviesResponse) {
            val movie = Movie(moviesResponse.title, moviesResponse.posterPath, moviesResponse.overview)
            movies.add(movie)
        }

        return movies
    }
}
