package br.com.fabricioandrade.filmesmvp.presenter

import br.com.fabricioandrade.filmesmvp.contract.MoviesContract
import br.com.fabricioandrade.filmesmvp.data.mapper.MovieMapper
import br.com.fabricioandrade.filmesmvp.data.network.ApiService
import br.com.fabricioandrade.filmesmvp.data.network.response.MoviesResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesPresenter(private var view: MoviesContract.MoviesView?): MoviesContract.MoviePresenter {

    override fun getMovies(title: String) {
        view?.showLoading()
        ApiService.getInstance()
            .searchMovies(title)
            .enqueue(object : Callback<MoviesResult> {
                override fun onResponse(call: Call<MoviesResult>, response: Response<MoviesResult>) {
                    if (response.isSuccessful) {
                        val movies = MovieMapper().responseToMovie(response.body()?.moviesResult!!)
                        view?.showMovies(movies);
                    } else {
                        view?.showError()
                    }
                }

                override fun onFailure(call: Call<MoviesResult>, t: Throwable) {
                    view?.showError()
                }
            })
    }

    override fun getMoviesByGenre(genre: String) {
        view?.showLoading()
        ApiService.getInstance()
            .searchByGenre(genre)
            .enqueue(object : Callback<MoviesResult> {
                override fun onResponse(call: Call<MoviesResult>, response: Response<MoviesResult>) {
                    if (response.isSuccessful) {
                        val movies = MovieMapper().responseToMovie(response.body()?.moviesResult!!)
                        view?.showMovies(movies);
                    } else {
                        view?.showError()
                    }
                }

                override fun onFailure(call: Call<MoviesResult>, t: Throwable) {
                    view?.showError()
                }
            })
    }

    override fun destroyView() {
        this.view = null
    }
}