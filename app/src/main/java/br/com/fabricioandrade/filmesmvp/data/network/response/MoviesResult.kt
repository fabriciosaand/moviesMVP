package br.com.fabricioandrade.filmesmvp.data.network.response

import com.squareup.moshi.Json

class MoviesResult {

    @Json(name = "results")
    val moviesResult: List<MovieResponse>? = null


}