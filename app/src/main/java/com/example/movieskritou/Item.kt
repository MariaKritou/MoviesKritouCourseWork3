package com.example.movieskritou

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
public class Item(id:Int, name: String, image: String,imageBack: String, vote: Double, releaseDate: String, overview: String) {

    @PrimaryKey(autoGenerate = false) var id: Int = id
    var name:String? = name
    var image: String? = image
    var imageBack: String? = imageBack
    var vote: Double = vote
    var releaseDate: String = releaseDate
    var overview: String = overview
}