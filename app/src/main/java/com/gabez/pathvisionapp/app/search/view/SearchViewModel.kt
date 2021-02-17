package com.gabez.pathvisionapp.app.search.view

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabez.pathvisionapp.app.statusHolders.ApiErrorHolder
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.PathStatus
import com.gabez.pathvisionapp.app.search.entities.SearchType
import com.gabez.pathvisionapp.app.search.entities.SkillForSearch
import com.gabez.pathvisionapp.domain.usecases.*
import com.gabez.pathvisionapp.domain.entities.PathObject
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class SearchViewModel(
    private val addPathUsecase: AddPathUsecase,
    private val deletePathUsecase: DeletePathUsecase,
    private val searchPathByKeywordUsecase: SearchPathByKeywordUsecase,
    private val searchPathBySkillUsecase: SearchPathBySkillUsecase,
    private val getPathsUsecase: GetLocalPathsUsecase,
    private val context: Context,
    private val apiError: ApiErrorHolder
) : ViewModel() {

    private val _searchData: MutableLiveData<ArrayList<PathForSearch>> = MutableLiveData()
    var searchData: LiveData<ArrayList<PathForSearch>> = _searchData

    val error: LiveData<String> = apiError.error

    val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val searchType: MutableLiveData<SearchType> = MutableLiveData(SearchType.BY_KEYWORD)

    @InternalCoroutinesApi
    fun searchPath(keyword: String) {
        if (keyword.isNotEmpty()) {
            when (searchType.value!!) {
                SearchType.BY_KEYWORD -> searchPathByKeyword(keyword)
                SearchType.BY_SKILL -> searchPathBySkill(keyword)
            }
        } else {
            searchData.value!!.clear()
        }
    }

    @InternalCoroutinesApi
    private fun searchPathByKeyword(keyword: String) = viewModelScope.launch {
        _isLoading.postValue(true)
        _searchData.value!!.clear()

        setupPathsForSearch(searchPathByKeywordUsecase.invoke(keyword))

    }.invokeOnCompletion { _isLoading.postValue(false) }

    @InternalCoroutinesApi
    private fun searchPathBySkill(skill: String) = viewModelScope.launch {
        _isLoading.postValue(true)
        _searchData.value!!.clear()
        setupPathsForSearch(searchPathBySkillUsecase.invoke(skill))


    }.invokeOnCompletion { _isLoading.postValue(false) }


    fun deletePath(path: PathForSearch) =
        viewModelScope.launch { deletePathUsecase.invoke(path.toPathObject()) }.invokeOnCompletion {
            val pathIndex = _searchData.value!!.indexOf(path)
            _searchData.value!![pathIndex].status = PathStatus.NOT_ADDED
            Toast.makeText(context, "Item deleted!", Toast.LENGTH_SHORT).show()
        }

    fun addPath(path: PathForSearch) =
        viewModelScope.launch { addPathUsecase.invoke(path.toPathObject()) }.invokeOnCompletion {
            val pathIndex = _searchData.value!!.indexOf(path)
            _searchData.value!![pathIndex].status = PathStatus.ADDED
            Toast.makeText(context, "Item added!", Toast.LENGTH_SHORT).show()
        }

    private suspend fun setupPathsForSearch(flow: Flow<List<PathObject>>) {
        flow.combine(getPathsUsecase.invoke()) { apiPaths, savedPaths ->

            for (savedPath in savedPaths) {
                for (apiPath in apiPaths) {
                    if (savedPath.title == apiPath.title) apiPath.status = PathStatus.ADDED
                    else apiPath.status = PathStatus.NOT_ADDED
                }
            }

            apiPaths

        }.collect { apiPaths ->

            _searchData.postValue(apiPaths.map { pathObject ->
                PathForSearch(
                    title = pathObject.title,
                    items = pathObject.items!!.map { skillObject -> SkillForSearch(title = skillObject.title) },
                    status = pathObject.status
                )
            } as ArrayList<PathForSearch>?)
        }
    }
}