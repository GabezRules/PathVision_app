package com.gabez.data.localDatabase.dbLogic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gabez.data.localDatabase.Converters
import com.gabez.data.localDatabase.entities.PathEntity
import com.gabez.data.localDatabase.entities.SkillEntity

@Database(entities = [PathEntity::class, SkillEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)

abstract class LocalDatabase: RoomDatabase() {
    abstract fun dao(): LocalDao

    companion object {
        fun getInstance(appContext: Context): LocalDatabase {
            return Room.databaseBuilder(appContext, LocalDatabase::class.java, LocalDatabase::class.simpleName!!)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}