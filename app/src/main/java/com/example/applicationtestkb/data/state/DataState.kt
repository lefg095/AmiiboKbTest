package com.example.applicationtestkb.data.state

sealed class DataState<out r> {
    data class Success<out T>(val response: T) : DataState<T>()
    data class Error(val error: Exception) : DataState<Nothing>()
    data class Loading(val message: String) : DataState<Nothing>()
}
