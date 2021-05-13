package com.example.terasoltask

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {

    @GET("moviedata.json")
    fun getMovies(): Call<List<Movie>>

    companion object {

        private const val BASE_URL = "http://test.terasol.in/"

        fun create(): Api {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(Api::class.java)

        }
    }

}