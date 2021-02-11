package com.gabez.pathvisionapp.data.remoteApiDatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabez.pathvisionapp.data.remoteApiDatabase.entities.PathFromServer

class ApiPathsHolder {
    private val _allPaths: MutableLiveData<List<PathFromServer>> = MutableLiveData(ArrayList())
    val allPaths: LiveData<List<PathFromServer>> = _allPaths

    private val _error: MutableLiveData<String> = MutableLiveData("")
    val error: LiveData<String> = _error

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun setPaths(paths: List<PathFromServer>) {
        _allPaths.postValue(paths)
    }

    fun setError(error: String){
        _error.postValue(error)
    }
}