package com.example.applicationtestkb.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterFavEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var amiiboSeries: String,
    var character: String,
    var gameSeries: String,
    var head: String,
    var image: String,
    var name: String,
    var tail: String,
    var type: String
)