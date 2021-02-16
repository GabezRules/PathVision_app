package com.gabez.pathvisionapp.data.dataSources

import androidx.lifecycle.MutableLiveData

interface ApiDatasource {
    fun searchByKeyword(keyword: String)
    fun searchBySkill(skill: String)
}