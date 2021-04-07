package com.example.mealgame

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.view.WindowManager
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.util.*



class MainActivity : AppCompatActivity() {


    private lateinit var midlay : ConstraintLayout
    private lateinit var rd_start: Button


    private lateinit var feeding: ImageView
    private lateinit var worm: ImageView
    private lateinit var lbl_gold: TextView


    lateinit var AdView: AdView

    data class Feed(val imageId: Int, val stage: Int, val per: Int, val hp: Int)

    val feeds = mutableListOf(
            Feed(R.drawable.bean1, 1, 1, 5),
            Feed(R.drawable.bean2, 1, 2, 10),
            Feed(R.drawable.bean3, 1, 3, 20),
            Feed(R.drawable.bean4, 1, 4, 30),
            Feed(R.drawable.bean5, 1, 5, 40),
            Feed(R.drawable.bean6, 1, 6, 50),
            Feed(R.drawable.bean7, 1, 7, 60),
            Feed(R.drawable.bean8, 1, 8, 70),
            Feed(R.drawable.bean9, 1, 9, 80),
            Feed(R.drawable.bean10, 1, 10, 90),

            Feed(R.drawable.ciz1, 2, 1, 100),
            Feed(R.drawable.ciz2, 2, 2, 110),
            Feed(R.drawable.ciz3, 2, 3, 120),
            Feed(R.drawable.ciz4, 2, 4, 130),
            Feed(R.drawable.ciz5, 2, 5, 140),
            Feed(R.drawable.ciz6, 2, 6, 150),
            Feed(R.drawable.ciz7, 2, 7, 160),
            Feed(R.drawable.ciz8, 2, 8, 170),
            Feed(R.drawable.ciz9, 2, 9, 180),
            Feed(R.drawable.ciz10, 2, 10, 190),

            Feed(R.drawable.tuna, 1, 3, 200)
    )

    private var currentFeed = feeds[0]

    data class Worm(val imageId: Int, val rd1: Int, val per: Int, val power: Int)

    val worms = mutableListOf(
            Worm(R.drawable.womo, 1, 1, 1),
            Worm(R.drawable.worm2, 1, 2, 10),
            Worm(R.drawable.worm1, 1, 3, 20)
    )

    private var currentWorm = worms[0]


    var gold = 0


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        midlay = findViewById(R.id.midlay)
        rd_start = findViewById(R.id.rd_start)
        feeding = findViewById(R.id.feeding)
        worm = findViewById(R.id.worm)

        lbl_gold = findViewById(R.id.lbl_gold)


// =================메뉴칸======================================================


        feeding.setImageResource(currentFeed.imageId)
        worm.setImageResource(currentWorm.imageId)

        rd_start.isVisible = true

        roundStart()

        touchStart()

//===================광 고 ================================================
            val adView = AdView(this)

            adView.adSize = AdSize.BANNER

            adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
// TODO: Add adView to your view hierarchy.

            MobileAds.initialize(this) {}
            AdView = findViewById(R.id.adView)
            val adRequest = AdRequest.Builder().build()
            AdView.loadAd(adRequest)

        }

    private fun touchStart() {
        midlay.setOnClickListener {

            gold += currentWorm.power
            lbl_gold.text = "$gold"
            showCurrentFeed()
        }
    }

    private fun roundStart() {
        rd_start.setOnClickListener {
            rd_start.isVisible = false
            feeding.isVisible = true

            feeding.animate().apply {
                duration = 1000
                translationY(400f)
            }.withEndAction {
                feeding.animate().apply {
                    duration = 100
                    scaleX(1.05f)
                }.withEndAction {
                    feeding.animate().apply {
                        startDelay = 100
                        duration = 100
                        scaleX(0.95f)
                    }.withEndAction {
                        feeding.animate().apply {
                            duration = 100
                            scaleX(1.05f)
                            scaleY(1.05f)
                        }.withEndAction {
                            feeding.animate().apply {
                                startDelay = 100
                                duration = 100
                                scaleX(0.95f)
                                scaleY(0.95f)
                            }.start()
                        }
                    }
                }
            }


            worm.isVisible = true
            worm.animate().apply {
                duration = 2500
                translationX(-230f)
            }.withEndAction {
                worm.animate().apply {
                    startDelay = 100
                    duration = 100
                    scaleX(0.95f)
                    scaleY(0.95f)
                }.start()
            }
        }

    }

    private fun showCurrentFeed() {
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
                feeding.setImageResource(newFeed.imageId)
            }
        rd_start.isVisible = true
        }


    override fun onDestroy() {
        super.onDestroy()
    }
}

