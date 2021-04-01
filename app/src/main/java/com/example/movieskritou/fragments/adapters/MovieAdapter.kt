package com.example.movieskritou.fragments.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.movieskritou.Item
import com.example.movieskritou.R
import com.google.android.material.card.MaterialCardView

class MovieAdapter(var context:Context, var arrayList:ArrayList<Item>) : BaseAdapter(){

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view:View = View.inflate(context, R.layout.card_item, null)
        val icons:ImageView = view.findViewById(R.id.icons)
        //val names:TextView = view.findViewById(R.id.name_text_view)
        val listitem : Item = arrayList.get(position)

        icons.setImageResource(listitem.image!!)
        //names.text = listitem.name

        return view
    }

    override fun getItem(position: Int): Any {
       return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }

}