package br.com.fabricioandrade.filmesmvp.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import br.com.fabricioandrade.filmesmvp.BuildConfig
import br.com.fabricioandrade.filmesmvp.R
import br.com.fabricioandrade.filmesmvp.model.Movie
import com.squareup.picasso.Picasso

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        val MOVIE = "MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val textViewOverview = findViewById<TextView>(R.id.textViewOverview)
        val imageViewPoster = findViewById<ImageView>(R.id.imageViewPoster)

        val movie = intent.getSerializableExtra(MOVIE) as Movie

        title = movie.title
        textViewOverview.text = movie.overview
        Picasso.get().load(BuildConfig.BASE_URL_IMAGE + movie.posterPath).into(imageViewPoster)

    }
}
