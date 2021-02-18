package com.gabez.pathvisionapp.dataModule.remoteApiDatabase.gateway

import com.gabez.pathvisionapp.dataModule.remoteApiDatabase.apiLogic.NetworkClient
import com.gabez.pathvisionapp.dataModule.remoteApiDatabase.gateway.ApiGateway
import com.gabez.pathvisionapp.domain.entities.PathObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class ApiGatewayImpl(private val service: NetworkClient): ApiGateway {

    override fun searchBySkill(skill: String): Flow<List<PathObject>> = service.searchBySkill(skill)
    override fun searchByKeyword(keyword: String): Flow<List<PathObject>> = service.searchByKeyword(keyword)
}