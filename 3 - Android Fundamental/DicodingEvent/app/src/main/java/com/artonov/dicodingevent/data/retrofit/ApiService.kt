package com.artonov.dicodingevent.data.retrofit

import com.artonov.dicodingevent.data.response.DetailEventResponse
import com.artonov.dicodingevent.data.response.EventResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("events")
    fun getEvents(
        @Query("active") id: String
    ): Call<EventResponse>

    @GET("events/{id}")
    fun getDetailEvent(
        @Path("id") id: String
    ): Call<DetailEventResponse>

    @GET("events")
    fun searchEvents(@Query("q") query: String): Call<EventResponse>
}