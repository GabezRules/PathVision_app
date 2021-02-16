package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.data.gateways.ApiGateway

class ApiDatasourceImpl(private val gateway: ApiGateway): ApiDatasource {

    override fun searchBySkill(skill: String) = gateway.searchBySkill(skill)
    override fun searchByKeyword(keyword: String) = gateway.searchByKeyword(keyword)
}