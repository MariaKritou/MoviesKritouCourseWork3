package com.example.movieskritou

import android.media.Image
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.AsyncTaskLoader
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetails : AppCompatActivity() {

    private var movieId: Int? = null
    private var arrayList: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        //Get the id of the movie clicked, that was passed from the intent
        movieId = intent.getIntExtra("MovieId", -1)

        val queue = Volley.newRequestQueue(this)
        val url =
            "https://api.themoviedb.org/3/movie/$movieId?api_key=9460908ad9c98b66c0024f11d4bc9bae"
        var urlCast =
            "https://api.themoviedb.org/3/movie/$movieId/credits?api_key=9460908ad9c98b66c0024f11d4bc9bae"

        // The images from json give paths , so the first static part is here and for the rest i concatenate it inside the response
        // and in the adapter i use Picasso to display the img from the path
        val imgurl = "https://image.tmdb.org/t/p/w500"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                movie_title.text = response.getString("original_title")
                synopsis.text = response.getString("overview")
                release_date.text = response.getString("release_date")
                avg_rate.text = response.getDouble("vote_average").toString()

                // Small image
                var image_path = response.getString("poster_path")
                var image = imgurl.plus(image_path)

                // Back image
                var image_back = response.getString("backdrop_path")
                var imageBack = imgurl.plus(image_back)

                // The images in json are given as a path so i display them using picasso
                val picasso = Picasso.get()
                picasso.load(imageBack).into(details_img)
                picasso.load(image).into(small_img)

            },
            Response.ErrorListener { Log.d("API", "that didn't work") })

        queue.add(jsonObjectRequest)

        // For the cast names list
        arrayList = ArrayList()
        setDataList()
    }

    private fun setDataList(): Void? {
        val queue = Volley.newRequestQueue(this)
        var urlCast ="https://api.themoviedb.org/3/movie/$movieId/credits?api_key=9460908ad9c98b66c0024f11d4bc9bae"

        val jsonObjectRequestCast = JsonObjectRequest(
            Request.Method.GET, urlCast, null,
            Response.Listener { response ->

                val jsonArray = response.getJSONArray("cast")
                for (i in 0 until jsonArray.length()) {

                    var item = jsonArray.getJSONObject(i)
                    var namee = item.getString("name")
                    arrayList?.add(namee)
                }
                cast.text = arrayList?.joinToString { it }
            },
            Response.ErrorListener { Log.d("API2", "that didn't work") })
        queue.add(jsonObjectRequestCast)
        return null
    }




}