package com.gabez.pathvisionapp.data.dataHolders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject

class DbPathsHolder {
    private val _allPaths: MutableLiveData<List<PathObject>> = MutableLiveData(ArrayList())
    val allPaths: LiveData<List<PathObject>> = _allPaths

    private val _allSkills: MutableLiveData<List<SkillObject>> = MutableLiveData(ArrayList())
    val allSkills: LiveData<List<SkillObject>> = _allSkills

    fun setPaths(paths: List<PathObject>, skills: List<SkillObject>) {
        _allSkills.postValue(skills)
        _allPaths.postValue(paths)
    }
}