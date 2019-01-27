package br.com.fabricioandrade.filmesmvp.contract

import br.com.fabricioandrade.filmesmvp.model.Movie

interface MoviesContract {

    interface MoviesView {

        fun showMovies(movies: List<Movie>)

        fun showError()

        fun showLoading()
    }

    interface MoviePresenter {

        fun getMovies(title: String)

        fun getMoviesByGenre(genre: String)

        fun destroyView()
    }

}