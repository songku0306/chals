
package com.example.wormtycoon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    data class Feed(val imageId: Int, val stage: Int, val perF: Int, val hp: Int)

    val feeds = mutableListOf(
            Feed(R.drawable.bean1, 1, 1, 10),
            Feed(R.drawable.bean2, 1, 2, 20),
            Feed(R.drawable.bean3, 1, 3, 30),
            Feed(R.drawable.bean4, 1, 4, 40),
            Feed(R.drawable.bean5, 1, 5, 50),
            Feed(R.drawable.bean6, 1, 6, 60),
            Feed(R.drawable.bean7, 1, 7, 70),
            Feed(R.drawable.bean8, 1, 8, 80),
            Feed(R.drawable.bean9, 1, 9, 90),
            Feed(R.drawable.bean10, 1, 10, 100)
    )

    private var currentFeed = feeds[0]

    data class Worm(val imageId: Int, val perW: Int, val power: Int)

    val worms = mutableListOf(
            Worm(R.drawable.womo, 1, 1)

    )

    private var currentWorm = worms[0]

    lateinit var iv_feed: ImageView
    lateinit var iv_worm: ImageView
    lateinit var tv_gold: TextView
    lateinit var tc_gold: ConstraintLayout

    var gold = 0
    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        iv_feed = findViewById(R.id.iv_feed)
        iv_worm = findViewById(R.id.iv_worm)

        iv_feed.setImageResource(currentFeed.imageId)
        iv_worm.setImageResource(currentWorm.imageId)

        tv_gold = findViewById(R.id.tv_gold)
        tc_gold = findViewById(R.id.tc_gold)

        roundSet()
    }

    fun roundSet() {

            iv_feed.animate().apply {
                duration = 1000
                translationY(400f)
            }.withEndAction {
                iv_feed.animate().apply {
                    duration = 100
                    scaleX(1.05f)
                }.withEndAction {
                    iv_feed.animate().apply {
                        startDelay = 100
                        duration = 100
                        scaleX(0.95f)
                    }.withEndAction {
                        iv_feed.animate().apply {
                            duration = 100
                            scaleX(1.05f)
                            scaleY(1.05f)
                        }.withEndAction {
                            iv_feed.animate().apply {
                                startDelay = 100
                                duration = 100
                                scaleX(0.95f)
                                scaleY(0.95f)
                            }.start()
                        }
                    }
                }
            }

//
//            iv_worm.animate().apply {
//                duration = 2500
//                translationX(-230f)
//            }.withEndAction {
//                iv_worm.animate().apply {
//                    duration = 50
//                    scaleXBy(0.5f)
//                }.withEndAction {
//                iv_worm.animate().apply {
//                    duration = 50
//                    scaleXBy(1.5f)
//                }.start()
//            }
//        }


            touchTap()
            powerUp()
    }

    private fun powerUp() {

    }

    fun touchTap() {
        tc_gold.setOnClickListener {
            gold += currentWorm.power
            num += currentWorm.power
            tv_gold.text = gold.toString()

            showCurrentFeed()
        }
    }


    fun showCurrentFeed() {
        var newFeed = feeds[0]
        for (feed in feeds) {
            if (gold >= feed.hp) {
                newFeed = feed
            }
            // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
            // you'll start producing more expensive desserts as determined by startProductionAmount
            // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
            // than the amount sold.
            else break
        }

        // If the new dessert is actually different than the current dessert, update the image
        if (newFeed != currentFeed) {
            currentFeed = newFeed
            iv_feed.setImageResource(newFeed.imageId)
        }
    }

}