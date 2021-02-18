package com.gabez.pathvisionapp

import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.gabez.pathvisionapp.dataModule.remoteApiDatabase.apiLogic.NetworkClient
import com.gabez.pathvisionapp.dataModule.remoteApiDatabase.statusHolders.ApiErrorHolder
import com.google.gson.reflect.TypeToken
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.junit.Test
import org.koin.core.KoinComponent
import java.lang.reflect.Type

class NetworkClientUnitTest: KoinComponent {

    @ExperimentalCoroutinesApi
    @Test
    fun isResponseByKeywordNotNull(){
        val client = NetworkClient(ApiErrorHolder())

        GlobalScope.launch(Dispatchers.IO) {
            client.searchByKeyword("Java").collect {
                    pathList -> assertNotNull(pathList)
            }
        }.invokeOnCompletion {
            assertEquals("", client.apiErrorHolder.error.value)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun isResponseByKeywordCorrect(){
        val client = NetworkClient(ApiErrorHolder())
        val pathListType: Type = object : TypeToken<List<PathObject>>() {}.type

        GlobalScope.launch(Dispatchers.IO) {
            client.searchByKeyword("Java").collect {
                pathList ->
                assertEquals(pathListType, pathList::class.java)
            }
        }.invokeOnCompletion {
            assertEquals("", client.apiErrorHolder.error.value)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun isResponseByTooShortKeywordWorking(){
        val client = NetworkClient(ApiErrorHolder())

        GlobalScope.launch(Dispatchers.IO) {
            client.searchByKeyword("a").collect {
                    pathList -> assertEquals(0, pathList.size)
            }
        }.invokeOnCompletion {
            assertEquals("", client.apiErrorHolder.error.value)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun isResponseBySkillNotNull(){
        val client = NetworkClient(ApiErrorHolder())

        GlobalScope.launch(Dispatchers.IO) {
            client.searchBySkill("Java").collect {
                    pathList -> assertNotNull(pathList)
            }
        }.invokeOnCompletion {
            assertEquals("", client.apiErrorHolder.error.value)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun isResponseBySkillCorrect(){
        val client = NetworkClient(ApiErrorHolder())
        val pathListType: Type = object : TypeToken<List<PathObject>>() {}.type

        GlobalScope.launch(Dispatchers.IO) {
            client.searchBySkill("Java").collect {
                    pathList ->
                assertEquals(pathListType, pathList::class.java)
            }
        }.invokeOnCompletion {
            assertEquals("", client.apiErrorHolder.error.value)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun isResponseByTooShortSkillWorking(){
        val client = NetworkClient(ApiErrorHolder())

        GlobalScope.launch(Dispatchers.IO) {
            client.searchBySkill("a").collect {
                    pathList -> assertEquals(0, pathList.size)
            }
        }.invokeOnCompletion {
            assertEquals("", client.apiErrorHolder.error.value)
        }
    }
}