package com.example.wormractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible

private lateinit var feed : ImageView
private lateinit var gif_worm: ImageView
private lateinit var start_rd : ImageButton
private lateinit var btn_power : ImageButton


data class Feed(val imageId: Int, val stage: Int, val round: Int, val healthPoint: Int)

val feeds = mutableListOf(
        Feed(R.drawable.tuna, 1, 1, 100),
        Feed(R.drawable.gif_ciz, 1, 2, 500)
)

private var currentFeed = feeds[0]


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feed = findViewById(R.id.feed)
        btn_power = findViewById(R.id.btn_power)


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

        btn_power.setOnClickListener {
            Mealworm.addWorm()
        }
        gif_worm = findViewById(R.id.gif_worm)

    }



//
//
//    fun wormPower() {
//        val gold = 0
//        gold = Feed.currentFeed.healthpower - Mealworm.worm1.power
//  }
}

object Mealworm {


     fun addWorm() {
        val addRandomWorm = (1..2).random()

        val mealworms = when(addRandomWorm) {
            1 -> R.drawable.gif_worm1
            else -> R.drawable.gif_worm
        }
        gif_worm.setImageResource(mealworms)

    }

}