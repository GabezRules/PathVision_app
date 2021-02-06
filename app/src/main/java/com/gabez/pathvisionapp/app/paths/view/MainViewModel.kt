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
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.PathStatus
import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.data.localDatabase.entities.SkillEntity
import com.gabez.pathvisionapp.domain.usecases.DeletePathUsecase
import com.gabez.pathvisionapp.domain.usecases.GetLocalPathsUsecase
import com.gabez.pathvisionapp.domain.usecases.GetLocalSkillsUsecase
import com.gabez.pathvisionapp.domain.usecases.UpdateSkillStatusUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

    init {
        getAllPaths()
    }

    private fun getAllPaths() = GlobalScope.launch(Dispatchers.IO) {
        _isLoading.postValue(true)
        _savedPaths.value!!.clear()

        _savedPaths.postValue(
            createPaths(
                getSkillsUsecase.invoke(),
                getPathsUsecase.invoke()
            )
        )

    }.invokeOnCompletion { _isLoading.postValue(false) }

    fun updateSkillStatus(skill: SkillForView, newStatus: SkillStatus) = GlobalScope.launch(Dispatchers.IO) {
        updateSkillUsecase.invoke(skill, newStatus)
    }.invokeOnCompletion { getAllPaths() }

    private fun createPaths(skillList: List<SkillEntity>, pathList: List<PathEntity>): ArrayList<PathForView> {

        val pathObjectList: ArrayList<PathForView> = ArrayList()

        for (pathEntityItem in pathList) {
            val itemsList: ArrayList<SkillForView> = ArrayList()

            for (skillEntityItem in skillList) {
                if (skillEntityItem.name in pathEntityItem.relatedSkills.split(";;")) itemsList.add(
                    SkillForView(
                        title = skillEntityItem.name,
                        status = when (skillEntityItem.status) {
                            0 -> SkillStatus.EMPTY
                            1 -> SkillStatus.IN_PROGRESS
                            2 -> SkillStatus.DONE
                            else -> SkillStatus.EMPTY
                        }
                    )
                )
            }

            pathObjectList.add(
                PathForView(
                    title = pathEntityItem.name,
                    items = itemsList
                )
            )
        }

        return pathObjectList
    }

    fun deletePath(path: PathForView) = viewModelScope.launch{ deletePathUsecase.invoke(path) }.invokeOnCompletion {
        _savedPaths.value!!.map { pathFromList -> if(pathFromList.title == path.title) _savedPaths.value!!.remove(pathFromList) }
        Toast.makeText(context, "Item deleted!", Toast.LENGTH_SHORT).show()
    }

}