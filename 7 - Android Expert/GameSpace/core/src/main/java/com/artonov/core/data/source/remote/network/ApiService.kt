package com.artonov.core.data.source.remote.network

import com.artonov.core.data.source.remote.response.GameResponse
import com.artonov.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("games")
    suspend fun getList(): ListGameResponse

   @GET("games/{id}")
   suspend fun getGameById(@Path("id") id: Int): GameResponse
}