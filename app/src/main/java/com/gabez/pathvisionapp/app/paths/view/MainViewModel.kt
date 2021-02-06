package com.gabez.pathvisionapp.app.paths.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabez.pathvisionapp.app.paths.entities.PathForView

class MainViewModel: ViewModel() {

    private val _savedPaths: MutableLiveData<ArrayList<PathForView>> = MutableLiveData(ArrayList())
    val savedPaths: LiveData<ArrayList<PathForView>> = _savedPaths

}