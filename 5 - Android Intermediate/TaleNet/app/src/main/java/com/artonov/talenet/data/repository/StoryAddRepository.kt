package com.artonov.talenet.data.repository

import android.util.Log
import com.artonov.talenet.data.preference.UserPreference
import com.artonov.talenet.data.response.ErrorResponse
import com.artonov.talenet.data.response.FileUploadResponse
import com.artonov.talenet.data.response.LoginResponse
import com.artonov.talenet.data.retrofit.ApiService
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import retrofit2.http.Multipart

class StoryAddRepository(
    private val apiService: ApiService,
    private val userPreference: UserPreference
    ) {

    suspend fun uploadImage(file: MultipartBody.Part, description: RequestBody): FileUploadResponse {
        return try {
            val response = apiService.uploadImage(file, description)
            Log.d("StoryAddRepository", "Success: $response")
            response
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            Log.e("StoryAddRepository", "Error: ${e.message}")
            throw Exception(errorBody.message)
        }
    }

    companion object {
        @Volatile
        private var instance: StoryAddRepository? = null

        fun getInstance(apiService: ApiService, userPreference: UserPreference): StoryAddRepository {
            return instance ?: synchronized(this) {
                instance ?: StoryAddRepository(apiService, userPreference).also { instance = it }
            }
        }
    }
}