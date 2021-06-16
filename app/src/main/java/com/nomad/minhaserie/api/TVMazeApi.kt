package com.nomad.minhaserie.api

import com.nomad.minhaserie.dataaccess.models.Show
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class TVMazeApi {
    companion object {
        fun getInstance(): Retrofit {
            val url = "https://api.tvmaze.com/"
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}

interface TVMazeEndpoints {
    @GET("show/")
    fun getShowByPage(@Query("page") page: Int): Call<List<Show>>

    @GET("search/shows/")
    fun getShowsByName(@Query("q") q: String): Call<List<Show>>
}
