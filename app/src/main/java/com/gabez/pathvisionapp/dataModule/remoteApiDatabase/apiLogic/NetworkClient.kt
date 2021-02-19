package com.gabez.pathvisionapp.dataModule.remoteApiDatabase.apiLogic

import com.gabez.pathvisionapp.dataModule.remoteApiDatabase.statusHolders.ApiErrorHolder
import com.gabez.pathvisionapp.dataModule.remoteApiDatabase.entities.PathFromServer
import com.gabez.pathvisionapp.app.domain.entities.PathObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO: Add response wrapper

class NetworkClient(val apiErrorHolder: ApiErrorHolder) {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: JobsApiService by lazy {
        retrofit.create(
            JobsApiService::class.java
        )
    }

    @ExperimentalCoroutinesApi
    fun searchByKeyword(keyword: String): Flow<List<PathObject>> = callbackFlow {
        if (keyword.length > 1) {
            apiErrorHolder.isLoading.postValue(true)
            service.searchByKeyword(
                keyword.toLowerCase(),
                TOKEN
            ).enqueue(object : Callback<List<PathFromServer>> {
                override fun onResponse(
                    call: Call<List<PathFromServer>>,
                    response: Response<List<PathFromServer>>
                ) {
                    if (response.isSuccessful) {
                        val pathList: List<PathFromServer>? = response.body()

                        if (pathList != null) {
                            if (pathList.isNotEmpty()) {
                                offer(pathList.map { pathItem -> pathItem.toPathObject() })

                                apiErrorHolder.setError("")
                                apiErrorHolder.isLoading.postValue(false)
                            } else apiErrorHolder.setError("Nothing found!")

                        } else apiErrorHolder.setError("Nothing found!")
                    } else {
                        apiErrorHolder.setError(response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<List<PathFromServer>>, t: Throwable) {
                    t.message?.let { apiErrorHolder.setError(it) }
                }

            })
        }else offer(ArrayList<PathObject>())

    }

    @ExperimentalCoroutinesApi
    fun searchBySkill(skill: String): Flow<List<PathObject>> = callbackFlow {
        if(skill.length > 1){
            apiErrorHolder.isLoading.postValue(false)

            service.searchBySkill(
                skill.toLowerCase(),
                TOKEN
            ).enqueue(object : Callback<List<PathFromServer>> {
                override fun onResponse(
                    call: Call<List<PathFromServer>>,
                    response: Response<List<PathFromServer>>
                ) {
                    if (response.isSuccessful) {
                        val pathList: List<PathFromServer>? = response.body()

                        if (pathList != null) {
                            if (pathList.isNotEmpty()) {
                                offer(pathList.map { pathItem -> pathItem.toPathObject() })

                                apiErrorHolder.setError("")
                                apiErrorHolder.isLoading.postValue(false)
                            } else apiErrorHolder.setError("Nothing found!")

                        } else apiErrorHolder.setError("Nothing found!")
                    } else {
                        apiErrorHolder.setError(response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<List<PathFromServer>>, t: Throwable) {
                    t.message?.let { apiErrorHolder.setError(it) }
                }

            })
        }else offer(ArrayList<PathObject>())

    }
}
