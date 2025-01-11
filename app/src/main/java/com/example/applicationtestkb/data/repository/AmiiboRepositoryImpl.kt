package com.example.applicationtestkb.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.applicationtestkb.data.model.BaseResponse
import com.example.applicationtestkb.data.model.CharacterAmiibo
import com.example.applicationtestkb.data.state.DataState
import com.example.applicationtestkb.domain.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AmiiboRepositoryImpl(
    private val context: Context,
    private val apiService: ApiService,
    private val amiiboDBRepositoryImpl: AmiiboDBRepositoryImpl
) : AmiiboRepository {

    override fun getAmiiboList(): Flow<DataState<BaseResponse<CharacterAmiibo>>> = flow {
        emit(DataState.Loading("Cargando elementos..."))
        try {
            val response: BaseResponse<CharacterAmiibo> = if (isConnectedToInternet()) {
                getCharactersOnline()
            } else {
                amiiboDBRepositoryImpl.getCharacters()
            }
            emit(DataState.Success(response = response))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }

    }

    private suspend fun getCharactersOnline(): BaseResponse<CharacterAmiibo> {
        val response = apiService.getAmiibo()
        response.amiiboList = response.amiiboList.take(100)
        amiiboDBRepositoryImpl.deleteAllCharacters()
        amiiboDBRepositoryImpl.addEverything(response.amiiboList)
        return amiiboDBRepositoryImpl.getCharacters()
    }

    private fun isConnectedToInternet(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}