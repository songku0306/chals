package com.example.calcuratetest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Frozen::class), version = 1)

abstract class FrozenDatabase : RoomDatabase() {

    abstract fun timeDAO() : FrozenDAO

    companion object {
        var INSTANCE : FrozenDatabase? = null

        fun getInstance(context: Context) : FrozenDatabase? {
            if(INSTANCE == null) {
                synchronized(FrozenDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FrozenDatabase::class.java, "time.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
