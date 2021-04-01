package com.example.movieskritou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetails : AppCompatActivity() {

    private var profileName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        //Get the name of the movie clicked, that was passed from the intent
        profileName = intent.getStringExtra("MovieName").toString()

        //Set image/title/favorite icon on page
        when(profileName){
            "AOT" -> {details_img.setBackgroundResource(R.drawable.aot);
                      movie_title.text = "AOT";
                      fav_details.setBackgroundResource(R.drawable.ic_baseline_favorite_24)}

            "JUJUTSU KAISEN" -> {details_img.setBackgroundResource(R.drawable.juju);
                                 movie_title.text = "JUJUTSU KAISEN";
                                 fav_details.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24) }

            "NARUTO" -> {details_img.setBackgroundResource(R.drawable.naruto);
                         movie_title.text = "NARUTO";
                         fav_details.setBackgroundResource(R.drawable.ic_baseline_favorite_24)}

            "SAO" -> {details_img.setBackgroundResource(R.drawable.sao);
                      movie_title.text = "SAO";
                      fav_details.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)}

            "BLACK CLOVER" -> {details_img.setBackgroundResource(R.drawable.blackcl);
                               movie_title.text = "BLACK CLOVER";
                               fav_details.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24) }

            else -> {details_img.setBackgroundResource(R.drawable.onep);
                     movie_title.text = "ONE PIECE";
                     fav_details.setBackgroundResource(R.drawable.ic_baseline_favorite_24) }
        }
    }
}