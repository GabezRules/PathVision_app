package com.gabez.pathvisionapp.app.search.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.domain.usecases.AddPathUsecase
import com.gabez.pathvisionapp.domain.usecases.DeletePathUsecase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val addPathUsecase: AddPathUsecase,
    private val deletePathUsecase: DeletePathUsecase
) : ViewModel() {

    fun deletePath(path: PathForSearch) = viewModelScope.launch{ deletePathUsecase.invoke(path) }
    fun addPath(path: PathForSearch) = viewModelScope.launch{ addPathUsecase.invoke(path) }
}