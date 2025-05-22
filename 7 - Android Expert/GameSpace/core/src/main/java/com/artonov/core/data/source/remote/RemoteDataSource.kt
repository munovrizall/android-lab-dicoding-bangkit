package com.artonov.core.data.source.remote

import android.util.Log
import com.artonov.core.data.source.remote.network.ApiResponse
import com.artonov.core.data.source.remote.network.ApiService
import com.artonov.core.data.source.remote.response.GameResponse
import com.artonov.core.data.source.remote.response.ListGameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllGames(): Flow<ApiResponse<ListGameResponse>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.results
                if (dataArray != null && dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameById(id: Int): Flow<ApiResponse<GameResponse>> {
        return flow {
            try {
                val response = apiService.getGameById(id)
                // Check if the response has a valid ID (not null)
                if (response.id != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}