package com.example.applicationtestkb.data.state

import com.example.applicationtestkb.data.model.CharacterAmiibo

sealed class FavoritesStateEvent {

    data class IsFavorite(
        val tail: String
    ): FavoritesStateEvent()

    data class AddOrRemoveFavorite(
        val favorite: CharacterAmiibo
    ): FavoritesStateEvent()

    object GetFavoritesList : FavoritesStateEvent()

}