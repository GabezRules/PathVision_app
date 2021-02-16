package com.gabez.pathvisionapp.data.gateways

interface ApiGateway {
    fun searchBySkill(skill: String)
    fun searchByKeyword(keyword: String)
}