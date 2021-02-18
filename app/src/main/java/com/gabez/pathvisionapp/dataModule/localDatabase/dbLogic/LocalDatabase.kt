package com.gabez.pathvisionapp.dataModule.localDatabase.dbLogic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gabez.pathvisionapp.dataModule.localDatabase.Converters
import com.gabez.pathvisionapp.dataModule.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.dataModule.localDatabase.entities.SkillEntity

@Database(entities = [PathEntity::class, SkillEntity::class], version = 2, exportSchema = false)
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