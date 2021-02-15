package com.gabez.pathvisionapp.data.dataSources

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.flow

class ApiDatasourceImpl: ApiDatasource {
    override var currentSkill: MutableLiveData<String> = MutableLiveData("")
    override var currentKeyword: MutableLiveData<String> = MutableLiveData("")

    override fun searchBySkill(skill: String){
        currentSkill.postValue(skill)
    }

    override fun searchByKeyword(keyword: String){
        currentKeyword.postValue(keyword)
    }
}