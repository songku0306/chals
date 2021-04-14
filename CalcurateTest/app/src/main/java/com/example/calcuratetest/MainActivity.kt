package com.example.calcuratetest

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {

    data class Feed(val imageId: Int, val stage: Int, val per: Int, val hp: Int)
    val feeds = mutableListOf(
            Feed(R.drawable.bean1, 1, 1, 0),
            Feed(R.drawable.bean2, 1, 2, 10),
            Feed(R.drawable.bean3, 1, 3, 20),

            Feed(R.drawable.ciz1, 2, 4, 30),
            Feed(R.drawable.ciz2, 2, 5, 40),
            Feed(R.drawable.ciz3, 2, 6, 50)

    )
    private var currentFeed = feeds[0]

    data class Worm(val imageId: Int, val power: Int)
    val worms = mutableListOf(
            Worm(R.drawable.womo, 3),
            Worm(R.drawable.womo, 4)
    )
    private var currentWorm = worms[0]

    lateinit var tv1 : TextView
    lateinit var touch : ConstraintLayout
    lateinit var feed : ImageView
    lateinit var worm : ImageView
    private lateinit var rd_start : Button

    var num = 0
    var gold = 0

    var pow = currentWorm.power
    var sow = currentFeed.hp

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feed = findViewById(R.id.feed)
        tv1 = findViewById(R.id.tv1)
        touch = findViewById(R.id.touch)
        worm = findViewById(R.id.worm)
        rd_start = findViewById(R.id.rd_start)
        rd_start.setOnClickListener {
            gold = 0
            num = 0
            showCurrentFeed()
        }
        feed.setImageResource(currentFeed.imageId)
        worm.setImageResource(currentWorm.imageId)

        goRound()

    }

    private fun goRound() {
        preRound()
        endRound()
    }

    private fun endRound() {
        if (num >= currentFeed.hp) {
            outFW()
        }
    }


    private fun preRound() {
        inFW()
        Handler().postDelayed(Runnable {
            touchUp()
       }, 1000)
    }
    private fun touchUp() {
        touch.setOnClickListener {
            num += pow
            gold += pow
            tv1.text = gold.toString()
            showCurrentFeed()
        }
    }
    private fun showCurrentFeed() {
        while (true) {
            var newFeed = feeds[0]
            for (feed in feeds) {
                if (num >= feed.hp) {
                    newFeed = feed
                } else break
            }
            if (newFeed != currentFeed) {
                currentFeed = newFeed
                feed.setImageResource(newFeed.imageId)
                preRound()
            }
        }
    }

    private fun inFW() {
        feed.isVisible = true
        worm.isVisible = true
        feed.animate().apply {
            duration = 1000
            translationY(400f)
        }.start()

        worm.animate().apply {
            duration = 1000
            translationX(-250f)
        }.start()    }
    private fun outFW() {
        feed.isVisible = false
        worm.isVisible = false
        feed.animate().apply {
            duration = 1000
            translationY(-400f)
        }.start()
        worm.animate().apply {
            duration = 1000
            translationX(250f)
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
