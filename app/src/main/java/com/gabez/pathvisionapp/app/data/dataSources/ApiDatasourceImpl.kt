package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.dataModule.remoteApiDatabase.gateway.ApiGateway
import com.gabez.pathvisionapp.app.domain.entities.PathObject
import kotlinx.coroutines.flow.Flow

class ApiDatasourceImpl(private val gateway: ApiGateway): ApiDatasource {

    override fun searchBySkill(skill: String): Flow<List<PathObject>> = gateway.searchBySkill(skill)
    override fun searchByKeyword(keyword: String): Flow<List<PathObject>> = gateway.searchByKeyword(keyword)
}