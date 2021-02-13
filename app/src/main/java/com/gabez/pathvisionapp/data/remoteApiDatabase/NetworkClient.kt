package com.gabez.pathvisionapp.data.remoteApiDatabase

import com.gabez.pathvisionapp.data.remoteApiDatabase.entities.PathFromServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkClient(private val apiDataHolder: ApiPathsHolder) : Callback<List<PathFromServer>> {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: JobsApiService by lazy { retrofit.create(JobsApiService::class.java) }

    fun searchByKeyword(keyword: String){
        apiDataHolder.isLoading.postValue(false)
        service.searchByKeyword(keyword.toLowerCase(), TOKEN).enqueue(this@NetworkClient)
    }

    fun searchBySkill(skill: String){
        apiDataHolder.isLoading.postValue(false)
        service.searchBySkill(skill.toLowerCase(), TOKEN).enqueue(this@NetworkClient)
    }

    override fun onFailure(call: Call<List<PathFromServer>>, t: Throwable) {
        t.message?.let { apiDataHolder.setError(it) }
    }

    override fun onResponse(
        call: Call<List<PathFromServer>>,
        response: Response<List<PathFromServer>>
    ) {
        if (response.isSuccessful) {
            val pathList: List<PathFromServer>? = response.body()

            if (pathList != null) {
                if(pathList.isNotEmpty()){
                    apiDataHolder.setPaths(pathList)
                    apiDataHolder.setError("")
                    apiDataHolder.isLoading.postValue(false)
                } else apiDataHolder.setError("Nothing found!")

            } else apiDataHolder.setError("Nothing found!")
        } else {
            apiDataHolder.setError(response.errorBody().toString())
        }
    }
}
