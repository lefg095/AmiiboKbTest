package com.example.applicationtestkb.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationtestkb.data.model.BaseResponse
import com.example.applicationtestkb.data.model.CharacterAmiibo
import com.example.applicationtestkb.data.repository.CharacterFavDBRepository
import com.example.applicationtestkb.data.repository.CharacterFavoritesRepository
import com.example.applicationtestkb.data.state.DataState
import com.example.applicationtestkb.data.state.FavoritesStateEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject
constructor(
    private val characterFavoritesRepository: CharacterFavoritesRepository
): ViewModel(){

    private val _favoritsResponseList = MutableLiveData<DataState<BaseResponse<CharacterAmiibo>>>()
    val favoritesListResponse: LiveData<DataState<BaseResponse<CharacterAmiibo>>> get() = _favoritsResponseList

    private val _addOrRemoveFavoriteResponse = MutableLiveData<DataState<String>>()
    val addOrRemoveFavoriteResponse: LiveData<DataState<String>> get() = _addOrRemoveFavoriteResponse

    private val _isFavoriteResponse = MutableLiveData<DataState<Boolean>>()
    val isFavoriteResponse: LiveData<DataState<Boolean>> get() = _isFavoriteResponse


    fun makeApiCall(favoritesStateEvent: FavoritesStateEvent){
        when(favoritesStateEvent){
            is FavoritesStateEvent.GetFavoritesList -> {
                viewModelScope.launch {
                    characterFavoritesRepository.getFavoritesList()
                        .collect{
                            _favoritsResponseList.value = it
                        }
                }

            }
            is FavoritesStateEvent.IsFavorite -> {
                viewModelScope.launch {
                    characterFavoritesRepository.isFavorite(
                        favoritesStateEvent.tail
                    ).collect {
                        _isFavoriteResponse.value = it
                    }
                }
            }

            is FavoritesStateEvent.AddOrRemoveFavorite -> {
                viewModelScope.launch {
                    characterFavoritesRepository.addOrRemoveFavorite(
                        favoritesStateEvent.favorite
                    ).collect {
                        _addOrRemoveFavoriteResponse.value = it
                    }
                }
            }
        }

    }

}