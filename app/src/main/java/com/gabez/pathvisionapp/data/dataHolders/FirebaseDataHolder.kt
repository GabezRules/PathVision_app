package com.gabez.pathvisionapp.data.dataHolders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject

class FirebaseDataHolder {
    private val _allPaths: MutableLiveData<List<PathObject>> = MutableLiveData(ArrayList())
    val allPaths: LiveData<List<PathObject>> = _allPaths

    fun restoreAllPaths(nList: List<PathObject>){
        _allPaths.postValue(nList)
    }

    private val _allSkills: MutableLiveData<List<SkillObject>> = MutableLiveData(ArrayList())
    val allSkills: LiveData<List<SkillObject>> = _allSkills

    fun restoreAllSkills(nList: List<SkillObject>){
        _allSkills.postValue(nList)
    }
}