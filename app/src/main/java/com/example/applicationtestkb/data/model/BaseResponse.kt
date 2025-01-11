package com.example.applicationtestkb.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T> (

    @Expose
    @SerializedName("amiibo")
    var amiiboList: List<T> = listOf()

)