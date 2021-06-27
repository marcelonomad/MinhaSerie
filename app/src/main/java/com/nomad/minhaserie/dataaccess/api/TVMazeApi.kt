package com.nomad.minhaserie.dataaccess.api

import com.nomad.minhaserie.dataaccess.models.Episode
import com.nomad.minhaserie.dataaccess.models.Season
import com.nomad.minhaserie.dataaccess.models.Show
import com.nomad.minhaserie.dataaccess.models.ShowByName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
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
    fun getShowsByPage(@Query("page") page: Int): Call<List<Show>>

    @GET("search/shows/")
    fun getShowsByName(@Query("q") q: String): Call<List<ShowByName>>

    @GET("shows/{id}")
    fun getShowById(@Path(value = "id") id: Int): Call<Show>

    @GET("shows/{id}/seasons")
    fun getSeasonsByShowId(@Path(value = "id") id: Int): Call<List<Season>>

    @GET("seasons/{id}/episodes")
    fun getEpisodesBySeasonId(@Path(value = "id") id: Int): Call<List<Episode>>

}
