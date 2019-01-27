package br.com.fabricioandrade.filmesmvp.data.network

import br.com.fabricioandrade.filmesmvp.data.network.response.MoviesResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("search/movie")
    fun searchMovies(
        @Query("query") query: String) : Call<MoviesResult>

    @GET("discover/movie")
    fun searchByGenre(
        @Query("with_genres") genre: String): Call<MoviesResult>

}