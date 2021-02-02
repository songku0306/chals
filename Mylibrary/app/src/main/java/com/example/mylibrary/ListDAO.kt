package com.example.mylibrary

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface BookDAO {

    @Insert(onConflict = REPLACE)
    fun insert(list : BookEntity)

    @Query("SELECT * FROM list")
    fun getAll() : List<BookEntity>

    @Delete
    fun delete(list: BookEntity)

}