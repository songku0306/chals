package com.example.mylibrary

import androidx.room.Entity

import androidx.room.PrimaryKey

@Entity(tableName = "list")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    var list : String = "")