package com.artonov.talenet.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.artonov.talenet.data.response.RegisterResponse
import com.artonov.talenet.data.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RegisterRepository(private val apiService: ApiService) {

    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        return try {
            val response = apiService.register(name, email, password)
            Log.d("RegisterRepository", "Success: $response")
            response
        } catch (e: Exception) {
            Log.e("RegisterRepository", "Error: ${e.message}")
            throw e
        }
    }
}
