package com.example.movieskritou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetails : AppCompatActivity() {

    private var movieTitle: String = ""
    private var moviePoster: String = ""
    private var moviePoster2: String = ""
    private var movieVote: Double? = null
    private var movieRelease: String = ""
    private var movieOverview: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        //Get the data of the movie clicked, that was passed from the intent
        movieTitle = intent.getStringExtra("MovieName").toString()
        moviePoster = intent.getStringExtra("MoviePoster").toString()
        moviePoster2 = intent.getStringExtra("MoviePoster2").toString()
        movieVote = intent.getDoubleExtra("MovieRate", 0.0)
        movieRelease = intent.getStringExtra("MovieRelease").toString()
        movieOverview = intent.getStringExtra("MovieOverview").toString()

        movie_title.text = movieTitle
        release_date.text = movieRelease
        avg_rate.text = movieVote.toString()
        synopsis.text = movieOverview

        // The images in json are given as a path so i display them using picasso
        val picasso = Picasso.get()
        picasso.load(moviePoster2).into(details_img)
        picasso.load(moviePoster).into(small_img)
    }
}