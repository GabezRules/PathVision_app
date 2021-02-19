package com.gabez.pathvisionapp

import com.gabez.pathvisionapp.app.domain.usecases.AddPathUsecase
import com.gabez.pathvisionapp.app.domain.usecases.DeletePathUsecase
import com.gabez.pathvisionapp.data.repo.AppRepositoryImpl
import com.gabez.pathvisionapp.dataModule.localDatabase.dbLogic.LocalDatabase
import com.gabez.pathvisionapp.dataModule.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.dataModule.localDatabase.entities.SkillEntity
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock

class LocalDatabaseTest {

    private fun initDbMock(): LocalDatabase = mock(LocalDatabase::class.java)

    @Test
    fun isAddingPathsWorking(){
        val db = initDbMock()
        val pathRepo = mock(AppRepositoryImpl::class.java)
        val addPathUsecase = AddPathUsecase(pathRepo)

        GlobalScope.launch (Dispatchers.IO) {
            addPathUsecase.invoke(mockPaths[0])
        }.invokeOnCompletion {
            GlobalScope.launch (Dispatchers.IO) {
                val allPathsSize = GlobalScope.launch(Dispatchers.IO) { db.dao().getAllPaths().size }
                Assert.assertEquals(1, allPathsSize)
            }
        }
    }

    @Test
    fun isLocalDbRequestWorking(){
        val db = initDbMock()

        val pathListType = object : TypeToken<List<PathEntity>>() {}.type

        GlobalScope.launch {
            val pathList = db.dao().getAllPaths()
            Assert.assertEquals(pathListType, pathList::class.java)
        }
    }

    @Test
    fun isLocalDbSkillRequestWorking(){
        val db = initDbMock()

        val skillListType = object : TypeToken<List<SkillEntity>>() {}.type

        GlobalScope.launch {
            val pathList = db.dao().getAllSkills()
            Assert.assertEquals(skillListType, pathList::class.java)
        }
    }

    @Test
    fun deleteSkillFromMain(){
        val db = initDbMock()
        val pathRepo = mock(AppRepositoryImpl::class.java)
        val addPathUsecase = AddPathUsecase(pathRepo)
        val deletePathUsecase = DeletePathUsecase(pathRepo)

        GlobalScope.launch (Dispatchers.IO) {
            addPathUsecase.invoke(mockPaths[0])
            addPathUsecase.invoke(mockPaths[1])
            addPathUsecase.invoke(mockPaths[2])
        }.invokeOnCompletion {
            GlobalScope.launch (Dispatchers.IO){
                deletePathUsecase.invoke(mockPaths[0])
            }.invokeOnCompletion {
                val allPathsSize = GlobalScope.launch(Dispatchers.IO) { db.dao().getAllPaths().size }
                Assert.assertEquals(2, allPathsSize)
            }
        }
    }
}