package com.gabez.pathvisionapp.data.remoteFirebaseDatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.entities.PathFirebaseEntity
import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.entities.SkillFirebaseEntity

class FirebaseDataHolder {
    private val _allPaths: MutableLiveData<List<PathFirebaseEntity>> = MutableLiveData(ArrayList())
    val allPaths: LiveData<List<PathFirebaseEntity>> = _allPaths

    fun restoreAllPaths(nList: List<PathFirebaseEntity>){
        _allPaths.postValue(nList)
    }

    private val _allSkills: MutableLiveData<List<SkillFirebaseEntity>> = MutableLiveData(ArrayList())
    val allSkills: LiveData<List<SkillFirebaseEntity>> = _allSkills

    fun restoreAllSkills(nList: List<SkillFirebaseEntity>){
        _allSkills.postValue(nList)
    }
}