package com.example.movieskritou.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.movieskritou.Item
import com.example.movieskritou.MovieDetails
import com.example.movieskritou.R
import com.example.movieskritou.fragments.adapters.MovieAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() , AdapterView.OnItemClickListener{

    private var arrayList: ArrayList<Item> ? = null
    private var movieAdapter:MovieAdapter ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arrayList = ArrayList()
        arrayList = setDataList()
        movieAdapter = activity?.let { MovieAdapter(it, arrayList!!) }

        gridview?.adapter = movieAdapter
        gridview.onItemClickListener = this
    }

    private fun setDataList(): ArrayList<Item>{

        val arrayList:ArrayList<Item> = ArrayList()

        arrayList.add(Item("AOT", R.drawable.aot))
        arrayList.add(Item("ONE PIECE", R.drawable.onep))
        arrayList.add(Item("NARUTO", R.drawable.naruto))
        arrayList.add(Item("SAO", R.drawable.sao))
        arrayList.add(Item("BLACK CLOVER", R.drawable.blackcl))
        arrayList.add(Item("JUJUTSU KAISEN", R.drawable.juju))

        return arrayList
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val items:Item = arrayList!!.get(position)

        Toast.makeText(activity, items.name, Toast.LENGTH_LONG).show()

        val intent = Intent(activity, MovieDetails::class.java)
        intent.putExtra("MovieName",items.name)
        startActivity(intent)
        }
}