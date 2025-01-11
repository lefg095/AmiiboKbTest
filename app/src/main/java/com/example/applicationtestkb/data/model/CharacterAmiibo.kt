package com.example.applicationtestkb.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
open class CharacterAmiibo(

    @Expose
    @SerializedName("amiiboSeries")
    var amiiboSeries: String = "",

    @Expose
    @SerializedName("character")
    var character: String = "",

    @Expose
    @SerializedName("gameSeries")
    var gameSeries: String = "",

    @Expose
    @SerializedName("head")
    var head: String = "",

    @Expose
    @SerializedName("image")
    var image: String = "",

    @Expose
    @SerializedName("name")
    var name: String = "",

    @Expose
    @SerializedName("tail")
    var tail: String = "",

    @Expose
    @SerializedName("type")
    var type: String = ""
): Parcelable