package com.example.applicationtestkb.data.repository

import com.example.applicationtestkb.data.model.BaseResponse
import com.example.applicationtestkb.data.model.CharacterAmiibo
import com.example.applicationtestkb.data.state.DataState
import kotlinx.coroutines.flow.Flow

interface AmiiboRepository {

    fun getAmiiboList() : Flow<DataState<BaseResponse<CharacterAmiibo>>>

}
