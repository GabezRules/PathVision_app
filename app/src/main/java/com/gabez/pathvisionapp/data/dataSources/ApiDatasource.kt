package com.gabez.pathvisionapp.data.dataSources

import androidx.lifecycle.MutableLiveData

interface ApiDatasource {
    val currentKeyword: MutableLiveData<String>
    val currentSkill: MutableLiveData<String>

    fun searchByKeyword(keyword: String)
    fun searchBySkill(skill: String)
}