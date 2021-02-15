package com.gabez.pathvisionapp.data.remoteApiDatabase

import com.gabez.pathvisionapp.data.remoteApiDatabase.entities.PathFromServer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApiService {
    @GET("api.php")
    fun searchByKeyword(@Query("name") keyword: String, @Query("token") token: String): Call<List<PathFromServer>>

    @GET("api.php")
    fun searchBySkill(@Query("skill") skill: String, @Query("token") token: String): Call<List<PathFromServer>>
}