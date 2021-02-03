package com.example.wormractice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(WormEntity::class), version = 1)
abstract class WormDatabase : RoomDatabase() {

    companion object {
        var INSTANCE : WormDatabase? = null

        fun getInstance(context: Context) : WormDatabase? {
            if(INSTANCE == null) {
                synchronized(WormDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        WormDatabase::class.java, "worm.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}