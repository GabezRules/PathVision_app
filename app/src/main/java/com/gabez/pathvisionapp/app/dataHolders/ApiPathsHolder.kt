package com.gabez.pathvisionapp.app.dataHolders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabez.pathvisionapp.app.search.entities.PathForSearch

class ApiPathsHolder {
    private val _allPaths: MutableLiveData<List<PathForSearch>> = MutableLiveData(ArrayList())
    val allPaths: LiveData<List<PathForSearch>> = _allPaths

    private val _error: MutableLiveData<String> = MutableLiveData("")
    val error: LiveData<String> = _error

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun setPaths(paths: List<PathForSearch>) {
        _allPaths.postValue(paths)
    }

    fun setError(error: String){
        _error.postValue(error)
    }
}