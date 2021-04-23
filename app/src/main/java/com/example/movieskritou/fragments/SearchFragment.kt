package com.example.movieskritou.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.movieskritou.R
import kotlinx.android.synthetic.main.fragment_search.*
import kotlin.collections.ArrayList

class SearchFragment : Fragment() {

    private var movies: ArrayList<String> ? = null
    private var arrayAdapter: ArrayAdapter<String> ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movies = ArrayList()
        autocomplete.threshold = 3

        autocomplete.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                //Empty the list before filling it again
               if (!movies.isNullOrEmpty()){
                    movies?.clear()
                }

                //Fill dropdown list
                setDataList(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun setDataList(title: CharSequence?): Void?{

        var queue = Volley.newRequestQueue(activity)

        // The url needs a query with the text given in EditText, i used string concatenation
        val url = "https://api.themoviedb.org/3/search/movie?api_key=9460908ad9c98b66c0024f11d4bc9bae&query="
        var query = url.plus(title)

        Log.d("QUERY-URL", query)

        val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, query,null,
                Response.Listener { response ->

                    val jsonArray = response.getJSONArray("results")
                    for(i in 0 until jsonArray.length()) {

                        val item = jsonArray.getJSONObject(i)
                        movies?.add(item.getString("original_title"))
                    }
                    arrayAdapter = activity?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, movies!!) }
                    autocomplete.setAdapter(arrayAdapter)
                    arrayAdapter?.notifyDataSetChanged()
                },
                Response.ErrorListener { Log.d("API", "that didn't work") })

        queue.add(jsonObjectRequest)
        return null
    }

}