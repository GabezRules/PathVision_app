package com.gabez.pathvisionapp.app.paths.view

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabez.pathvisionapp.app.paths.entities.PathForView
import com.gabez.pathvisionapp.app.paths.entities.SkillForView
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.domain.usecases.DeletePathUsecase
import com.gabez.pathvisionapp.domain.usecases.GetLocalPathsUsecase
import com.gabez.pathvisionapp.domain.usecases.GetLocalSkillsUsecase
import com.gabez.pathvisionapp.domain.usecases.UpdateSkillStatusUsecase
import com.gabez.pathvisionapp.domain.entities.PathObject
import com.gabez.pathvisionapp.domain.entities.SkillObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(
    private val getPathsUsecase: GetLocalPathsUsecase,
    private val getSkillsUsecase: GetLocalSkillsUsecase,
    private val updateSkillUsecase: UpdateSkillStatusUsecase,
    private val deletePathUsecase: DeletePathUsecase,
    private val context: Context
) : ViewModel() {

    private val _savedPaths: MutableLiveData<ArrayList<PathForView>> = MutableLiveData(ArrayList())
    val savedPaths: MutableLiveData<ArrayList<PathForView>> = _savedPaths

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init { getAllPaths() }

    private fun getAllPaths() = GlobalScope.launch(Dispatchers.IO) {
        _isLoading.postValue(true)
        _savedPaths.value!!.clear()

        runBlocking {

            val skillsFlow = getSkillsUsecase.invoke()
            val pathsFlow = getPathsUsecase.invoke()

            pathsFlow.combine(skillsFlow){ pathObjList, skillObjList ->
                createPaths(skillObjList, pathObjList)
            }.collect {

                val pathsForView = it.map { pathObject -> PathForView(

                    title = pathObject.title,
                    items = pathObject.items!!.map { skillItem -> SkillForView(title = skillItem!!.title, status = skillItem.status)}

                )} as ArrayList<PathForView>?

                _savedPaths.postValue(pathsForView)
            }
        }

    }.invokeOnCompletion { _isLoading.postValue(false) }

    fun updateSkillStatus(skill: SkillForView, newStatus: SkillStatus) = GlobalScope.launch(Dispatchers.IO) {
        updateSkillUsecase.invoke(SkillObject(title = skill.title, status = newStatus))
    }.invokeOnCompletion { getAllPaths() }

    private fun createPaths(skillList: List<SkillObject>, pathList: List<PathObject>): ArrayList<PathObject> {

        val pathObjectList: ArrayList<PathObject> = ArrayList()

        for (pathItem in pathList) {
            val itemsList: ArrayList<SkillObject> = ArrayList()

            for (skillItem in skillList) {
                var skillTitleList: List<String>? = pathItem.items?.map { skillItem -> skillItem.title }

                if (skillTitleList?.contains(skillItem.title) == true) itemsList.add(
                    SkillObject(
                        title = skillItem.title,
                        status = skillItem.status
                    )
                )
            }

            pathObjectList.add(
                PathObject(
                    title = pathItem.title,
                    items = itemsList
                )
            )
        }

        return pathObjectList
    }

    fun deletePath(path: PathForView) = viewModelScope.launch{ deletePathUsecase.invoke(path.toPathObject()) }.invokeOnCompletion {
        getAllPaths()
        Toast.makeText(context, "Item deleted!", Toast.LENGTH_SHORT).show()
    }

}