package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.data.remoteApiDatabase.NetworkClient
import com.gabez.pathvisionapp.data.remoteApiDatabase.entities.PathFromServer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiDatasourceImpl(private val client: NetworkClient): ApiDatasource {
    override fun searchByKeyword(keyword: String) = client.searchByKeyword(keyword)
    override fun searchBySkill(skill: String) = client.searchBySkill(skill)
}