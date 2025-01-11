package com.example.applicationtestkb.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterFavDao {

    @Query("SELECT * FROM CharacterFavEntity")
    suspend fun getCharactersFavorites(): List<CharacterFavEntity>

    @Query("SELECT * FROM CharacterFavEntity WHERE tail = :tail")
    suspend fun getCharactersFavoritesByTail(tail: String): List<CharacterFavEntity>

    @Insert
    suspend fun addCharacterFav(item: CharacterFavEntity) : Long

    @Query("DELETE FROM CharacterFavEntity WHERE tail = :tail")
    suspend fun deleteCharacterFav(tail: String) : Int

}