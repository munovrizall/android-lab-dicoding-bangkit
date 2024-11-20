package com.artonov.talenet.data.repository

import android.util.Log
import com.artonov.talenet.data.preference.UserPreference
import com.artonov.talenet.data.response.ErrorResponse
import com.artonov.talenet.data.response.LoginResponse
import com.artonov.talenet.data.response.StoryResponse
import com.artonov.talenet.data.retrofit.ApiService
import com.google.gson.Gson
import retrofit2.HttpException

class StoryRepository(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {
    suspend fun getStories(): StoryResponse {
        return try {
            val response = apiService.getStories()
            Log.d("StoryRepository", "Success: $response")
            response
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            Log.e("StoryRepository", "Error: ${e.message}")
            throw Exception(errorBody.message)
        }
    }

    companion object {
        @Volatile
        private var instance: StoryRepository? = null

        fun getInstance(apiService: ApiService, userPreference: UserPreference): StoryRepository {
            return instance ?: synchronized(this) {
                instance ?: StoryRepository(apiService, userPreference).also { instance = it }
            }
        }
    }
}