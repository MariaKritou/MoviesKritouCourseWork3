/*
package com.example.movieskritou.room

import androidx.room.TypeConverter
import com.example.movieskritou.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieTypeConverter {
    var gson = Gson()

    fun movieToString(item: Item): String {
        return gson.toJson(item)
    }

    @TypeConverter
    fun stringToMovie(data:String): Item {
        var listType = object : TypeToken<Item>() {}.type
        return gson.fromJson(data, listType)
    }
}*/
