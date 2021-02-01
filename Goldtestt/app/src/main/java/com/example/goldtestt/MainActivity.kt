package com.example.goldtestt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.goldtestt.Mealworm.power
import java.util.*
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {

    private lateinit var gif_worm: ImageView
    private lateinit var lbl_gold : TextView
    private lateinit var livegold : TextView
    private lateinit var btn_worm : ImageButton
    private lateinit var feed : ImageView
    private lateinit var start_rd : ImageButton


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
        start_rd = findViewById(R.id.start_rd)



        startTimer()

        btn_worm.setOnClickListener {
            gold -= 10
            Mealworm
        }

        gif_worm.isVisible = false
        feed.isVisible = false
        start_rd.setOnClickListener {
            start_rd.isGone = true
            gif_worm.isVisible = true
            feed.isVisible = true
            gif_worm.animate().apply {
                duration = 2500
                translationX(-200f)
                start()
            }

        }


    }



//    private fun eatring() {
//        lbl_gold = lbl_gold?.minus(power)
//    }

    private fun startTimer(): TextView {
        timerTask = timer(period = 500) {
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


object Mealworm {

    var power = 0
init {

    var power = 1

}


    val mealworms = mutableListOf(
            "wormlv1",
            "wormlv2",
            "wormlv3"
    )


//    fun wormPower() {
//        if Mealworm = MainActivity.feed {
//        power++
//    }
    }

