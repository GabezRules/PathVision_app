package com.gabez.pathvisionapp.dataModule.localDatabase

import androidx.room.TypeConverter

class Converters {
    val STRING_SEPARATOR = ";;"

    @TypeConverter
    fun toSkillsStringList(value: String): List<String> = value.split(STRING_SEPARATOR)

    @TypeConverter
    fun fromSkillsList(value: List<String>): String = value.joinToString(STRING_SEPARATOR)
}