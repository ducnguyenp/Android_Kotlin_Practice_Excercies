package com.example.practiceallexercise.activities.retrofit

import retrofit.Call
import retrofit.http.GET

interface RetrofitService {
    @GET("todos/1")
    fun getUser(
//        @Query("lat") lat: Double,
    ): Call<UserResponse>
}