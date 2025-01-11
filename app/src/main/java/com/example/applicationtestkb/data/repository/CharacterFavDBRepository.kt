package com.example.applicationtestkb.data.repository

import com.example.applicationtestkb.data.model.BaseResponse
import com.example.applicationtestkb.data.model.CharacterAmiibo

interface CharacterFavDBRepository {

    suspend fun getCharactersFav(): BaseResponse<CharacterAmiibo>

    suspend fun getCharactersFavoritesByTail(tail: String): Int

    suspend fun add(characterModel: CharacterAmiibo): Long

    suspend fun delete(characterModel: CharacterAmiibo): Int

}
