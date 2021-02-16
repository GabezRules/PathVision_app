package com.gabez.data.remoteApiDatabase.gateway

import com.gabez.data.remoteApiDatabase.apiLogic.NetworkClient
import com.gabez.pathvisionapp.data.gateways.ApiGateway
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ApiGatewayImpl(private val service: NetworkClient): ApiGateway {

    override fun searchBySkill(skill: String) = service.searchBySkill(skill)
    override fun searchByKeyword(keyword: String) = service.searchByKeyword(keyword)
}