package com.gabez.pathvisionapp.app.search.view

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.PathStatus
import com.gabez.pathvisionapp.app.search.entities.searchMockData
import com.gabez.pathvisionapp.domain.usecases.AddPathUsecase
import com.gabez.pathvisionapp.domain.usecases.DeletePathUsecase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val addPathUsecase: AddPathUsecase,
    private val deletePathUsecase: DeletePathUsecase,
    private val context: Context
) : ViewModel() {

    private val _mockData: MutableLiveData<ArrayList<PathForSearch>> = MutableLiveData()
    var searchData: LiveData<ArrayList<PathForSearch>> = _mockData

    init { _mockData.value = searchMockData }

    fun deletePath(path: PathForSearch) = viewModelScope.launch{ deletePathUsecase.invoke(path) }.invokeOnCompletion {
        _mockData.value!!.map { pathFromList -> if(pathFromList.title == path.title) pathFromList.status = PathStatus.NOT_ADDED }
        Toast.makeText(context, "Item deleted!", Toast.LENGTH_SHORT).show()
    }

    fun addPath(path: PathForSearch) = viewModelScope.launch{ addPathUsecase.invoke(path) }.invokeOnCompletion {
        _mockData.value!!.map { pathFromList -> if(pathFromList.title == path.title) pathFromList.status = PathStatus.ADDED }
        Toast.makeText(context, "Item added!", Toast.LENGTH_SHORT).show()
    }
}