package com.example.movies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApi {
    @GET("/")
    fun getMovies(@Query("apikey") key: String, @Query("s") title: String): Call<MovieList>


}