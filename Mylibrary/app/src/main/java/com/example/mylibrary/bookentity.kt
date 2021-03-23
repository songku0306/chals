package com.example.mylibrary

import android.widget.TextView
import androidx.room.Entity

import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


@Entity(tableName = "list")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    var list : String = ""
    )