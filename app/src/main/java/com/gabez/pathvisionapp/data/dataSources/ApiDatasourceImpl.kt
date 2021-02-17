package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.data.gateways.ApiGateway
import com.gabez.pathvisionapp.domain.entities.PathObject
import kotlinx.coroutines.flow.Flow

class ApiDatasourceImpl(private val gateway: ApiGateway): ApiDatasource {

    override fun searchBySkill(skill: String): Flow<List<PathObject>> = gateway.searchBySkill(skill)
    override fun searchByKeyword(keyword: String): Flow<List<PathObject>> = gateway.searchByKeyword(keyword)
}