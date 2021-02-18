package com.gabez.pathvisionapp

import android.content.Context
import com.gabez.pathvisionapp.dataModule.localDatabase.dbLogic.LocalDatabase
import com.gabez.pathvisionapp.dataModule.localDatabase.entities.PathEntity
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock

class LocalDatabaseTest {

    @Test
    fun isLocalDbRequestWorking(){
        val db = LocalDatabase.getInstance(mock(Context::class.java))

        val pathListType = object : TypeToken<List<PathEntity>>() {}.type

        GlobalScope.launch {
            val pathList = db.dao().getAllPaths()
            Assert.assertEquals(pathListType, pathList::class.java)
        }
    }
}