package br.com.fabricioandrade.filmesmvp.model

import java.io.Serializable

class Movie(val title: String,
            val posterPath: String?,
            val overview : String) : Serializable