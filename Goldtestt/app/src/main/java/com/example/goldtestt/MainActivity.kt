package com.example.goldtestt

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {

    private lateinit var gif_worm: ImageView
    private lateinit var lbl_gold : TextView
    private lateinit var livegold : TextView
    private lateinit var btn_worm : ImageButton
    private lateinit var feed : ImageView

    private lateinit var egpush1 : ImageButton
    private lateinit var egg1 : ImageView


    private var gold = 0
    private var timerTask: Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gif_worm = findViewById(R.id.gif_worm)
        lbl_gold = findViewById(R.id.lbl_gold)
        btn_worm = findViewById(R.id.button)
        livegold = findViewById(R.id.lbl_gold)
        feed = findViewById(R.id.feed)

        egg1 = findViewById(R.id.egg1)
        egpush1 = findViewById(R.id.egpush1)



        startTimer()


        btn_worm.setOnClickListener {
            gold -= 10

        }

        gif_worm.animate().apply {
                duration = 1500
                translationX(-200f)
                start()
            }
        }






















    private fun startTimer(): TextView {
        timerTask = timer(period=500) {
            gold++
            runOnUiThread {
                lbl_gold?.text = "$gold"
            }
        }
        return livegold
    }


    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    private fun stopTimer() {
        timerTask?.cancel()
    }
}


