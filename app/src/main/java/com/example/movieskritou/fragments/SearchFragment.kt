package com.example.movieskritou.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.movieskritou.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movie = listOf<String>("Click to select movie","AOT", "ONE PIECE", "JUJUTSU KAISEN", "SAO", "BLACK CLOVER", "NARUTO")
        val arrayAdapter = activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, movie) }

        arrayAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when(parent?.getItemAtPosition(position).toString()){
                    "AOT" -> search_fav.setBackgroundResource(R.drawable.aot)
                    "ONE PIECE" -> search_fav.setBackgroundResource(R.drawable.onep)
                    "BLACK CLOVER" -> search_fav.setBackgroundResource(R.drawable.blackcl)
                    "SAO" -> search_fav.setBackgroundResource(R.drawable.sao)
                    "JUJUTSU KAISEN" -> search_fav.setBackgroundResource(R.drawable.juju)
                    "NARUTO" -> search_fav.setBackgroundResource(R.drawable.naruto)
                    else -> search_fav.setBackgroundResource(R.drawable.linear_grad)
                }
            }
        }
    }
}