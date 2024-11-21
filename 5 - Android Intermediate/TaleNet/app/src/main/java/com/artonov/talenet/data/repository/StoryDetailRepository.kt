package com.artonov.talenet.data.repository

import android.util.Log
import com.artonov.talenet.data.preference.UserPreference
import com.artonov.talenet.data.response.ErrorResponse
import com.artonov.talenet.data.response.StoryDetailResponse
import com.artonov.talenet.data.response.StoryResponse
import com.artonov.talenet.data.retrofit.ApiService
import com.google.gson.Gson
import retrofit2.HttpException

class StoryDetailRepository(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {
    suspend fun getStoryDetail(id: String): StoryDetailResponse {
        return try {
            val response = apiService.getStoryDetail(id)
            Log.d("StoryDetailRepository", "Success: $response")
            response
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            Log.e("StoryDetailRepository", "Error: ${e.message}")
            throw Exception(errorBody.message)
        }
    }

    companion object {
        @Volatile
        private var instance: StoryDetailRepository? = null

        fun getInstance(apiService: ApiService, userPreference: UserPreference): StoryDetailRepository {
            return instance ?: synchronized(this) {
                instance ?: StoryDetailRepository(apiService, userPreference).also { instance = it }
            }
        }
    }
}