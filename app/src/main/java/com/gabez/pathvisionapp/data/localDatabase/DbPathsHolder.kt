package com.gabez.pathvisionapp.data.localDatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.data.localDatabase.entities.SkillEntity

class DbPathsHolder {
    private val _allPaths: MutableLiveData<List<PathEntity>> = MutableLiveData(ArrayList())
    val allPaths: LiveData<List<PathEntity>> = _allPaths

    private val _allSkills: MutableLiveData<List<SkillEntity>> = MutableLiveData(ArrayList())
    val allSkills: LiveData<List<SkillEntity>> = _allSkills

    fun setPaths(paths: List<PathEntity>, skills: List<SkillEntity>) {
        _allSkills.postValue(skills)
        _allPaths.postValue(paths)
    }
}