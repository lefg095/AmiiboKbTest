package com.example.applicationtestkb.domain

import com.example.applicationtestkb.data.model.BaseResponse
import com.example.applicationtestkb.data.model.CharacterAmiibo
import retrofit2.http.GET

interface ApiService {

    @GET("api/amiibo/")
    suspend fun getAmiibo() : BaseResponse<CharacterAmiibo>

}