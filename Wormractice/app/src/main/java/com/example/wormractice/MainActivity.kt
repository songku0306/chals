package com.example.wormractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible

private lateinit var feed : ImageView
private lateinit var lbl_gold : TextView
private lateinit var gif_worm: ImageView
private lateinit var start_rd : ImageButton
private lateinit var btn_power : ImageButton
private lateinit var btn_power1 : ImageButton


data class Feed(val imageId: Int, val stage: Int, val round: Int, val healthPoint: Int)

val feeds = mutableListOf(
    Feed(R.drawable.beans, 1, 1, 100),
    Feed(R.drawable.gif_ciz, 1, 2, 500),
    Feed(R.drawable.tuna, 1, 3, 2500)
)

private var currentFeed = feeds[0]


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feed = findViewById(R.id.feed)
        lbl_gold = findViewById(R.id.lbl_gold)
        gif_worm = findViewById(R.id.gif_worm)
        start_rd = findViewById(R.id.start_rd)
        btn_power = findViewById(R.id.btn_power)
        btn_power1 = findViewById(R.id.btn_power1)


        feed.setImageResource(currentFeed.imageId)

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

        btn_power1.setOnClickListener {
            currentFeed.plus(1)
            runOnUiThread {
                lbl_gold.text = "$lbl_gold"
            }
        }

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

