package com.example.fruits.interfaces

import com.example.fruits.models.FruitList
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("getFruits")
    fun getFruitsList(): Call<FruitList>
}