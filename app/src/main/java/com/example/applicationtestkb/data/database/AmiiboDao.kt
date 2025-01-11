package com.example.applicationtestkb.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AmiiboDao {
    @Query("SELECT * from AmiiboCharacterEntity")
    suspend fun getCharacters(): List<AmiiboCharacterEntity>

    @Insert
    suspend fun addCharacter(item: AmiiboCharacterEntity)

    @Query("DELETE FROM AmiiboCharacterEntity")
    suspend fun deleteAllCharacters()

}
