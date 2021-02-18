package com.gabez.pathvisionapp.dataModule.remoteApiDatabase.apiLogic

import com.gabez.pathvisionapp.dataModule.remoteApiDatabase.entities.PathFromServer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApiService {
    @GET("api.php")
    fun searchByKeyword(@Query("name") keyword: String, @Query("token") token: String): Call<List<PathFromServer>>

    @GET("api.php")
    fun searchBySkill(@Query("skill") skill: String, @Query("token") token: String): Call<List<PathFromServer>>
}