package com.dicoding.tourismapp.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.tourismapp.core.data.source.remote.network.ApiResponse
import com.dicoding.tourismapp.core.data.source.remote.network.ApiService
import com.dicoding.tourismapp.core.data.source.remote.response.ListTourismResponse
import com.dicoding.tourismapp.core.data.source.remote.response.TourismResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    suspend fun getAllTourism(): Flow<ApiResponse<List<TourismResponse>>> {
       return flow {
           try {
               val response = apiService.getList()
               val dataArray = response.places
               if (dataArray.isNotEmpty()) {
                   emit(ApiResponse.Success(response.places))
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

