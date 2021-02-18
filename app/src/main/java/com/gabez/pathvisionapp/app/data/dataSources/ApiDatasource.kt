package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.app.domain.entities.PathObject
import kotlinx.coroutines.flow.Flow

interface ApiDatasource {
    fun searchByKeyword(keyword: String): Flow<List<PathObject>>
    fun searchBySkill(skill: String): Flow<List<PathObject>>
}