package com.example.wormractice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface WormDAO {

    @Insert(onConflict = 1)
    fun insert(list : WormEntity)

    @Query("SELECT * FROM worm")
    fun getAll() : List<WormEntity>

    @Delete
    fun delete(list: WormEntity)

}