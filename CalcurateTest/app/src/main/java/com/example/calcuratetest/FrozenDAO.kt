package com.example.calcuratetest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface FrozenDAO {

    @Insert(onConflict = REPLACE)
    fun insert(time : Frozen)

    @Query("SELECT * FROM time")
    fun getAll() : List<Frozen>

    @Delete
    fun delete(time: Frozen)

}