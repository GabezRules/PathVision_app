package com.gabez.pathvisionapp.app.search.view

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabez.pathvisionapp.data.dataHolders.ApiPathsHolder
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.PathStatus
import com.gabez.pathvisionapp.app.search.entities.SearchType
import com.gabez.pathvisionapp.data.dataHolders.DbPathsHolder
import com.gabez.pathvisionapp.domain.usecases.AddPathUsecase
import com.gabez.pathvisionapp.domain.usecases.DeletePathUsecase
import com.gabez.pathvisionapp.domain.usecases.SearchPathByKeywordUsecase
import com.gabez.pathvisionapp.domain.usecases.SearchPathBySkillUsecase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class SearchViewModel(
    private val addPathUsecase: AddPathUsecase,
    private val deletePathUsecase: DeletePathUsecase,
    private val searchPathByKeywordUsecase: SearchPathByKeywordUsecase,
    private val searchPathBySkillUsecase: SearchPathBySkillUsecase,
    private val context: Context,
    private val allPathsDb: DbPathsHolder,
    private val allPathsApi: ApiPathsHolder
) : ViewModel() {

    private val _mockData: MutableLiveData<ArrayList<PathForSearch>> = MutableLiveData()
    var searchData: LiveData<ArrayList<PathForSearch>> = _mockData

    val error: LiveData<String> = allPathsApi.error
    val isLoading: LiveData<Boolean> = allPathsApi.isLoading

    val searchType: MutableLiveData<SearchType> = MutableLiveData(SearchType.BY_KEYWORD)

    init {
        //_mockData.value = searchMockData
        allPathsDb.allPaths.observeForever { refreshMockData() }
        allPathsApi.allPaths.observeForever { allPaths -> _mockData.postValue(ArrayList(allPaths)) }
    }

    @InternalCoroutinesApi
    fun searchPath(keyword: String){
        if(keyword.isNotEmpty()){
            when(searchType.value!!){
                SearchType.BY_KEYWORD -> searchPathByKeyword(keyword)
                SearchType.BY_SKILL -> searchPathBySkill(keyword)
            }
        }else{
            searchData.value!!.clear()
        }
    }

    @InternalCoroutinesApi
    private fun searchPathByKeyword(keyword: String) = viewModelScope.launch {
        _mockData.value!!.clear()
        searchPathByKeywordUsecase.invoke(keyword)
    }

    @InternalCoroutinesApi
    private fun searchPathBySkill(skill: String) = viewModelScope.launch {
        _mockData.value!!.clear()
        searchPathBySkillUsecase.invoke(skill)
    }


    fun deletePath(path: PathForSearch) =
        viewModelScope.launch { deletePathUsecase.invoke(path.toPathEntity()) }.invokeOnCompletion {
            refreshMockData()
            Toast.makeText(context, "Item deleted!", Toast.LENGTH_SHORT).show()
        }

    fun addPath(path: PathForSearch) =
        viewModelScope.launch { addPathUsecase.invoke(path) }.invokeOnCompletion {
            refreshMockData()
            Toast.makeText(context, "Item added!", Toast.LENGTH_SHORT).show()
        }

    private fun refreshMockData() {
        for (foundPath in allPathsDb.allPaths.value!!) {
            for (searchPath in _mockData.value!!) {
                if (foundPath.name == searchPath.title) searchPath.status = PathStatus.ADDED
                else searchPath.status = PathStatus.NOT_ADDED
            }
        }
    }
}