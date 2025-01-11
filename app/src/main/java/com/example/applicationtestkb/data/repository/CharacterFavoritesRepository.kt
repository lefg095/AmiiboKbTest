package com.example.applicationtestkb.data.repository

import com.example.applicationtestkb.data.model.BaseResponse
import com.example.applicationtestkb.data.model.CharacterAmiibo
import com.example.applicationtestkb.data.state.DataState
import kotlinx.coroutines.flow.Flow

interface CharacterFavoritesRepository {

    fun getFavoritesList(): Flow<DataState<BaseResponse<CharacterAmiibo>>>

    fun isFavorite(tail: String): Flow<DataState<Boolean>>

    fun addOrRemoveFavorite(character: CharacterAmiibo): Flow<DataState<String>>

}
