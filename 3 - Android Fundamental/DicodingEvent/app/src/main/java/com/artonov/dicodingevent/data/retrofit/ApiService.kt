package com.artonov.dicodingevent.data.retrofit

import com.artonov.dicodingevent.data.response.EventResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("events")
    fun getEvents(
        @Query("active") id: String
    ): Call<EventResponse>
}