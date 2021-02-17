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
import com.gabez.pathvisionapp.data.dataHolders.DbPathsHolder
import com.gabez.pathvisionapp.domain.usecases.DeletePathUsecase
import com.gabez.pathvisionapp.domain.usecases.GetLocalPathsUsecase
import com.gabez.pathvisionapp.domain.usecases.GetLocalSkillsUsecase
import com.gabez.pathvisionapp.domain.usecases.UpdateSkillStatusUsecase
import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPathsUsecase: GetLocalPathsUsecase,
    private val getSkillsUsecase: GetLocalSkillsUsecase,
    private val updateSkillUsecase: UpdateSkillStatusUsecase,
    private val deletePathUsecase: DeletePathUsecase,
    private val context: Context,
    private val allPaths: DbPathsHolder
) : ViewModel() {

    private val _savedPaths: MutableLiveData<ArrayList<PathForView>> = MutableLiveData(ArrayList())
    val savedPaths: MutableLiveData<ArrayList<PathForView>> = _savedPaths

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getAllPaths()

        allPaths.allPaths.observeForever {
            _isLoading.postValue(true)
            _savedPaths.postValue(
                createPaths(
                    allPaths.allSkills.value!!,
                    allPaths.allPaths.value!!
                )
            )

            _isLoading.postValue(false)
        }
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
        updateSkillUsecase.invoke(SkillObject(title = skill.title, status = newStatus))
    }.invokeOnCompletion { getAllPaths() }

    private fun createPaths(skillList: List<SkillObject>, pathList: List<PathObject>): ArrayList<PathForView> {

        val pathObjectList: ArrayList<PathForView> = ArrayList()

        for (pathEntityItem in pathList) {
            val itemsList: ArrayList<SkillForView> = ArrayList()

            for (skillEntityItem in skillList) {
                var skillTitleList: List<String>? = pathEntityItem.items?.map { skillItem -> skillItem.title }

                if (skillTitleList?.contains(skillEntityItem.title) == true) itemsList.add(
                    SkillForView(
                        title = skillEntityItem.title,
                        status = skillEntityItem.status
                    )
                )
            }

            pathObjectList.add(
                PathForView(
                    title = pathEntityItem.title,
                    items = itemsList
                )
            )
        }

        return pathObjectList
    }

    fun deletePath(path: PathForView) = viewModelScope.launch{ deletePathUsecase.invoke(path.toPathObject()) }.invokeOnCompletion {
        _savedPaths.value!!.remove(path)
        Toast.makeText(context, "Item deleted!", Toast.LENGTH_SHORT).show()
    }

}