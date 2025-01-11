package com.example.applicationtestkb.data.repository

import com.example.applicationtestkb.data.model.BaseResponse
import com.example.applicationtestkb.data.model.CharacterAmiibo
import com.example.applicationtestkb.data.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterFavoritesRepositoryImpl(
    private val characterFavDBRepository: CharacterFavDBRepository
) : CharacterFavoritesRepository {

    override fun getFavoritesList(): Flow<DataState<BaseResponse<CharacterAmiibo>>> = flow {
        emit(DataState.Loading("Cargando favoritos..."))
        try {
            val response = characterFavDBRepository.getCharactersFav()
            emit(DataState.Success(response = response))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

    override fun addOrRemoveFavorite(character: CharacterAmiibo): Flow<DataState<String>> = flow{
        emit(DataState.Loading("Trabajando..."))
        try {
            val result = characterFavDBRepository.getCharactersFavoritesByTail(character.tail)
            if (result == 0) {
                val adding = characterFavDBRepository.add(character)
                if (adding > 0) {
                    emit(DataState.Success("Añadido correctamente"))
                } else {
                    emit(DataState.Error(Exception("Error al insertar el favorito")))
                }
            } else {
                val removing = characterFavDBRepository.delete(character)

                if (removing > 0) {
                    emit(DataState.Success("Removido correctamente"))
                } else {
                    emit(DataState.Error(Exception("Error al remover el favorito")))
                }
            }
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

    override fun isFavorite(tail: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading("Validando favorito..."))
        try {
            val result = characterFavDBRepository.getCharactersFavoritesByTail(tail)

            if (result > 0) {
                emit(DataState.Success(true))
            } else {
                emit(DataState.Success(false))
            }
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}