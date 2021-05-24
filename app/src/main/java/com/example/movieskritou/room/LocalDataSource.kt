/*
package com.example.movieskritou.room

import com.example.movieskritou.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieDAO: MovieDAO){

    suspend fun readDatabase(): Flow<List<Item>> {
        return movieDAO.getFavMovies()
    }

    suspend fun insertMovie(item: Item){
        movieDAO.insertFavMovie(item)
    }
}*/
