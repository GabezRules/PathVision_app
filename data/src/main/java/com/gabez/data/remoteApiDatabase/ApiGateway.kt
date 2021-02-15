package com.gabez.data.remoteApiDatabase

import com.gabez.data.remoteApiDatabase.apiLogic.NetworkClient
import com.gabez.pathvisionapp.data.dataSources.ApiDatasource
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ApiGateway(private val apiDatasource: ApiDatasource, private val service: NetworkClient) {
    init{
        apiDatasource.currentKeyword.observeForever { keyword ->
            if(keyword != "") service.searchByKeyword(keyword)
        }

        apiDatasource.currentSkill.observeForever { skill ->
            if(skill != "") service.searchBySkill(skill)
        }
    }
}