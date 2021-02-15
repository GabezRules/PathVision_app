package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.data.remoteApiDatabase.entities.PathFromServer
import retrofit2.Call

interface ApiDatasource {
    fun searchByKeyword(keyword: String)
    fun searchBySkill(skill: String)
}