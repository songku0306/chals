package com.example.calcuratetest

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    data class Feed(val imageId: Int, val stage: Int, val per: Int, val hp: Int)
    val feeds = mutableListOf(
            Feed(R.drawable.bean1, 1, 1, 1),
            Feed(R.drawable.bean2, 1, 2, 2),
            Feed(R.drawable.bean3, 1, 3, 3),

            Feed(R.drawable.ciz1, 2, 1, 4),
            Feed(R.drawable.ciz2, 2, 2, 5),
            Feed(R.drawable.ciz3, 2, 3, 6)

    )
    private var currentFeed = feeds[0]
    private var currentStage = feeds[1]


    data class Worm(val imageId: Int, val power: Int)
    val worms = mutableListOf(
            Worm(R.drawable.womo, 1),
            Worm(R.drawable.womo, 2)
    )
    private var currentWorm = worms[0]

    lateinit var tv1 : TextView
    lateinit var touch : ConstraintLayout
    lateinit var feed : ImageView
    lateinit var worm : ImageView
    lateinit var rd_start : Button

    var num = 0
    var pow = currentWorm.power

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feed = findViewById(R.id.feed)
        tv1 = findViewById(R.id.tv1)
        touch = findViewById(R.id.touch)
        worm = findViewById(R.id.worm)
        rd_start = findViewById(R.id.rd_start)

        feed.setImageResource(currentFeed.imageId)
        worm.setImageResource(currentWorm.imageId)

        rd_start.isVisible = true
        feed.isVisible = false
        worm.isVisible = false

        gameStart()

    }

    private fun gameStart() {
        rd_start.setOnClickListener { mfun() }
        roundEnd()
    }
    private fun roundEnd() {
        val newStage = feeds[1]
        if (newStage != currentStage) {
            rd_start.isVisible = true
        }
    }


    private fun mfun() {
        rd_start.isVisible = false
        feed.isVisible = true
        worm.isVisible = true

        touch.setOnClickListener {
            num += pow
            tv1.text = "$num"
            showCurrentFeed()
        }
    }
    private fun showCurrentFeed() {
            var newFeed = feeds[0]
            for (feed in feeds) {
                if (num >= feed.hp) {
                    newFeed = feed
                } else break
            }
            if (newFeed != currentFeed) {
                currentFeed = newFeed
                feed.setImageResource(newFeed.imageId)
            }
        }

    override fun onDestroy() {
        super.onDestroy()
    }
}
