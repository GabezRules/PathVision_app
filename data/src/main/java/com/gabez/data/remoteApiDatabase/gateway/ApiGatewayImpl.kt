package com.gabez.data.remoteApiDatabase.gateway

import com.gabez.data.remoteApiDatabase.apiLogic.NetworkClient
import com.gabez.pathvisionapp.data.gateways.ApiGateway
import com.gabez.pathvisionapp.domain.entities.PathObject
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@InternalCoroutinesApi
class ApiGatewayImpl(private val service: NetworkClient): ApiGateway {

    override fun searchBySkill(skill: String): Flow<List<PathObject>> = service.searchBySkill(skill)
    override fun searchByKeyword(keyword: String): Flow<List<PathObject>> = service.searchByKeyword(keyword)
}