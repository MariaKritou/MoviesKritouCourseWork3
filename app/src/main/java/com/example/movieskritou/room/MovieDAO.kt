/*
package com.example.movieskritou.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieskritou.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavMovie(item: Item)

    @Query("SELECT * FROM movies_table ORDER BY id ASC")
    fun getFavMovies() : Flow<List<Item>>
}*/
