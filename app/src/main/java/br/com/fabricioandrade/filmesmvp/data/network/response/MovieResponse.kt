package br.com.fabricioandrade.filmesmvp.data.network.response

import com.squareup.moshi.Json

class MovieResponse(
    @field:Json(name = "title") var title: String,
    @field:Json(name = "poster_path") var posterPath: String,
    @field:Json(name = "overview") var overview:String
)