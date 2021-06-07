package com.example.calcuratetest

import android.widget.TextView
import androidx.room.Entity

import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


@Entity(tableName = "time")
data class Frozen(
        @PrimaryKey(autoGenerate = true)
        var pictureFood : Int, var time : String)

