package com.example.movieskritou.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.movieskritou.Item
import com.example.movieskritou.MovieDetails
import com.example.movieskritou.R
import com.example.movieskritou.fragments.adapters.MovieAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_home.*

class FavoritesFragment : Fragment(), AdapterView.OnItemClickListener {

    private var arrayList: ArrayList<Item>? = null
    private var movieAdapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arrayList = ArrayList()
        setDataList()

        movieAdapter = activity?.let { MovieAdapter(it, arrayList!!) }
        gridviewfav?.adapter = movieAdapter
        gridviewfav.onItemClickListener = this
    }

    private fun setDataList(): Void? {

        val queue = Volley.newRequestQueue(activity)
        val url =
            "https://api.themoviedb.org/3/movie/now_playing?api_key=9460908ad9c98b66c0024f11d4bc9bae"

        // The images from json give paths , so the first static part is here and for the rest i concatenate it inside the response
        // and in the adapter i use Picasso to display the img from the path
        val imgurl = "https://image.tmdb.org/t/p/w500"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                val jsonArray = response.getJSONArray("results")
                for (i in 0 until jsonArray.length()) {

                    val item = jsonArray.getJSONObject(i)

                    // Small image
                    var image_path = item.getString("poster_path")
                    var image = imgurl.plus(image_path)

                    // Back image
                    var image_back = item.getString("backdrop_path")
                    var image2 = imgurl.plus(image_back)

                    var title = item.getString("original_title")
                    var vote = item.getDouble("vote_average")
                    var overview = item.getString("overview")
                    var released = item.getString("release_date")

                    arrayList?.add(Item(title, image, image2, vote, released, overview))
                }
                movieAdapter?.notifyDataSetChanged()
            },
            Response.ErrorListener { Log.d("API", "that didn't work") })

        queue.add(jsonObjectRequest)

        return null
    }

    // Go to movie's details page
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val items: Item = arrayList!!.get(position)

        Toast.makeText(activity, items.name, Toast.LENGTH_LONG).show()

        val intent = Intent(activity, MovieDetails::class.java)
        intent.putExtra("MovieName", items.name)
        intent.putExtra("MovieName", items.name)
        intent.putExtra("MoviePoster", items.image)
        intent.putExtra("MoviePoster2", items.imageBack)
        intent.putExtra("MovieRate", items.vote)
        intent.putExtra("MovieRelease", items.releaseDate)
        intent.putExtra("MovieOverview", items.overview)

        startActivity(intent)
    }
}