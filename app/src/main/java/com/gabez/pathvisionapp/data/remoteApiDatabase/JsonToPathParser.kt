package com.gabez.pathvisionapp.data.remoteApiDatabase

import com.gabez.pathvisionapp.data.remoteApiDatabase.entities.PathFromServer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class JsonToPathParser {
    private val gson = Gson()
    private val type: Type = object: TypeToken<List<PathFromServer>>() {}.type

    fun parseServerResponse(jsonString: String): List<PathFromServer>{
        return gson.fromJson(jsonString, type)
    }
}