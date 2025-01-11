package com.example.applicationtestkb.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationtestkb.data.model.BaseResponse
import com.example.applicationtestkb.data.model.CharacterAmiibo
import com.example.applicationtestkb.data.repository.AmiiboRepository
import com.example.applicationtestkb.data.state.AmiiboStateEvent
import com.example.applicationtestkb.data.state.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmiiboViewModel
@Inject
constructor(
    private val amiiboRepository: AmiiboRepository
) : ViewModel() {

    private val _amiiboResponseList = MutableLiveData<DataState<BaseResponse<CharacterAmiibo>>>()
    val amiiboListResponse: LiveData<DataState<BaseResponse<CharacterAmiibo>>> get() = _amiiboResponseList


    fun getAmiiboList(amiiboStateEvent: AmiiboStateEvent){
        when(amiiboStateEvent){
            is AmiiboStateEvent -> {
                viewModelScope.launch {
                    amiiboRepository.getAmiiboList()
                        .collect {
                            _amiiboResponseList.value = it
                    }
                }
            }
        }
    }

}