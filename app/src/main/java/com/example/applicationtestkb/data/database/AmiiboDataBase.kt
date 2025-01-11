package com.example.applicationtestkb.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        AmiiboCharacterEntity::class,
        CharacterFavEntity::class
               ],
    version = 2
)
abstract class AmiiboDataBase: RoomDatabase() {

    abstract fun amiiboDao(): AmiiboDao

    abstract fun characterFavDao(): CharacterFavDao

}