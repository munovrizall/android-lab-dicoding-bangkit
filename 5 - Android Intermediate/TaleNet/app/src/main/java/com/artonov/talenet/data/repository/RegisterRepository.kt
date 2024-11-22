package com.artonov.talenet.data.repository

import android.util.Log
import com.artonov.talenet.data.response.ErrorResponse
import com.artonov.talenet.data.response.RegisterResponse
import com.artonov.talenet.data.retrofit.ApiService
import com.google.gson.Gson
import retrofit2.HttpException

class RegisterRepository(private val apiService: ApiService) {

    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        return try {
            val response = apiService.register(name, email, password)
            Log.d("RegisterRepository", "Success: $response")
            response
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            Log.e("RegisterRepository", "Error: ${e.message}")
            throw Exception(errorBody.message)
        }
    }
}
