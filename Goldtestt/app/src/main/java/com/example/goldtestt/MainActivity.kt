package com.example.goldtestt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {

    private lateinit var lbl_gold: TextView

    private var gold = 0
    private var timerTask: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lbl_gold = findViewById(R.id.lbl_gold)

        startTimer()

    }

    private fun startTimer() {
        timerTask = timer(period=1000) {
            gold++
            runOnUiThread {
                lbl_gold?.text = "$gold"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    private fun stopTimer() {
        timerTask?.cancel()
    }
}