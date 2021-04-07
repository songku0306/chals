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
            Feed(R.drawable.bean4, 1, 4, 4),
            Feed(R.drawable.bean5, 1, 5, 5),
            Feed(R.drawable.bean6, 1, 6, 6),
            Feed(R.drawable.bean7, 1, 7, 7),
            Feed(R.drawable.bean8, 1, 8, 8),
            Feed(R.drawable.bean9, 1, 9, 9),
            Feed(R.drawable.bean10, 1, 10, 10),

            Feed(R.drawable.bean1, 2, 1, 11),
            Feed(R.drawable.bean2, 2, 2, 12),
            Feed(R.drawable.bean3, 2, 3, 13),
            Feed(R.drawable.bean4, 2, 4, 14),
            Feed(R.drawable.bean5, 2, 5, 15),
            Feed(R.drawable.bean6, 2, 6, 16),
            Feed(R.drawable.bean7, 2, 7, 17),
            Feed(R.drawable.bean8, 2, 8, 18),
            Feed(R.drawable.bean9, 2, 9, 19),
            Feed(R.drawable.bean10, 2, 10, 20)
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

        feed.setImageResource(currentFeed.imageId)
        worm.setImageResource(currentWorm.imageId)

        mfun()

    }

    private fun mfun() {
        touch.setOnClickListener {

            num += pow
            tv1.text = "$num"
            showCurrentFeed()
            roundEnd()
        }
    }

    private fun roundEnd() {
        var newStage =feeds[1]
        for (stage in feeds) {
            if (num >= stage.hp) {
                newStage = stage
            } else break
        }
        if (newStage != currentStage) {
            currentStage = newStage
            feed.setImageResource(newStage.imageId)
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
