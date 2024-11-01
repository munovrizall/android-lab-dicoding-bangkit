package com.dicoding.asclepius.data.remote.retrofit

import com.dicoding.asclepius.data.remote.response.DetailNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("q") q: String,
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("apiKey") apiKey: String
    ): Call<DetailNewsResponse>
}