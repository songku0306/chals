package com.example.mylibrary

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BookEntity::class), version = 1)

abstract class BookDatabase : RoomDatabase() {
    abstract fun listDAO() : BookDAO

    companion object {
        var INSTANCE : BookDatabase? = null

        fun getInstance(context: Context) : BookDatabase? {
            if(INSTANCE == null) {
                synchronized(BookDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    BookDatabase::class.java, "list.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}