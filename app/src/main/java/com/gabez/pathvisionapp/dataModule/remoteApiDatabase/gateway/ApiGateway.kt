package com.gabez.pathvisionapp.dataModule.remoteApiDatabase.gateway

import com.gabez.pathvisionapp.domain.entities.PathObject
import kotlinx.coroutines.flow.Flow

interface ApiGateway {
    fun searchBySkill(skill: String): Flow<List<PathObject>>
    fun searchByKeyword(keyword: String): Flow<List<PathObject>>
}