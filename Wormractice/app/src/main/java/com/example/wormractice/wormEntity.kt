package com.example.wormractice

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "worm")
data class WormEntity(
        @PrimaryKey(autoGenerate = true)
        var id : Long?,
        var power : Long)




data class Feed(val imageId: Int,
                val stage: Int,
                val round: Int,
                val healthPoint: Int)

val feeds = mutableListOf(
        Feed(R.drawable.tuna, 1, 1, 100),
        Feed(R.drawable.gif_ciz, 1, 2, 500)
)
var currentFeed = feeds[0]