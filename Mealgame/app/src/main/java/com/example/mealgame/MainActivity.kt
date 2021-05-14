package com.example.mealgame

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.view.WindowManager
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import pl.droidsonroids.gif.GifImageView


lateinit var touch : ConstraintLayout
lateinit var feed: GifImageView
lateinit var worm: ImageView
lateinit var gfworm : GifImageView
lateinit var tv_gold: TextView
lateinit var menubtn : ImageButton
lateinit var dam : TextView
lateinit var AdView: AdView
lateinit var efec : GifImageView
lateinit var efec1 : GifImageView
lateinit var shopbtn : ImageButton


class MainActivity : AppCompatActivity() {

    data class Feed(val imageId: Int, val stage: Int, val per: Int, val hp: Int)
    private val feeds = mutableListOf(
            Feed(R.drawable.bean1, 1, 1, 5),
            Feed(R.drawable.bean2, 1, 2, 2),
            Feed(R.drawable.bean3, 1, 3, 3),
            Feed(R.drawable.bean4, 1, 4, 4),
            Feed(R.drawable.bean5, 1, 5, 5),
            Feed(R.drawable.bean6, 1, 6, 6),
            Feed(R.drawable.bean7, 1, 7, 7),
            Feed(R.drawable.bean8, 1, 8, 8),
            Feed(R.drawable.bean9, 1, 9, 9),
            Feed(R.drawable.bean10, 1, 10, 10),

            Feed(R.drawable.ciz_init, 2, 1, 11),
            Feed(R.drawable.ciz1, 2, 1, 12),
            Feed(R.drawable.ciz2, 2, 2, 13),
            Feed(R.drawable.ciz3, 2, 3, 14),
            Feed(R.drawable.ciz4, 2, 4, 15),
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
    private val worms = mutableListOf(
            Worm(R.drawable.womo, 1, 10, 1),
            Worm(R.drawable.womo, 1, 20, 2),
            Worm(R.drawable.womo, 1, 30, 3)
    )
    private var currentWorm = worms[0]

    var gold = 0
    var num = 0
    var pow = currentWorm.power

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        touch = findViewById(R.id.touch)
        feed = findViewById(R.id.feed)
        worm = findViewById(R.id.worm)
        gfworm = findViewById(R.id.gfworm)
        tv_gold = findViewById(R.id.tv_gold)
        feed.setImageResource(currentFeed.imageId)
        worm.setImageResource(currentWorm.imageId)
        menubtn = findViewById(R.id.menubtn)
        dam = findViewById(R.id.dam)
        efec = findViewById(R.id.efec)
        efec1 = findViewById(R.id.efec1)
        shopbtn = findViewById(R.id.shopbtn)

        worm.isVisible = false
        dam.isVisible = false
        efec.isVisible = false
        efec1.isVisible = false
        dam.text = "-" + pow.toString()

        inWorm()
        outFeed()

        Handler(Looper.getMainLooper()).postDelayed( {
            upFeed()
            downFeed()
            worm.isVisible = true
            inFeed()
            touchUp()
            gfworm.isVisible = false
        }, 1000)

        shopbtn.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
            Log.d("button", "shopbtn clicked")
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }


        menubtn.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            Log.d("button", "menubtn clicked")
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

//===================광 고 ================================================
        val adView = AdView(this)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        MobileAds.initialize(this) {}
        AdView = findViewById(R.id.adView_main)
        val adRequest = AdRequest.Builder().build()
        AdView.loadAd(adRequest)

    }



    private fun touchUp() {
        touch.setOnClickListener {
            gold += pow
            num += pow
            tv_gold.text = gold.toString()
            effect()
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
    private fun effect() {
        upFeed()
        downFeed()
        showEfec()
        showEfec1()
        showDam()
        downDam()
    }


    private fun showEfec() {
        efec.isVisible = true
        val animator = ValueAnimator.ofFloat(1f,0f)
        animator.duration = 400
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            efec.alpha = animationValue
        }
    }
    private fun showEfec1() {
        efec1.isVisible = true
        val animator = ValueAnimator.ofFloat(1f,0f)
        animator.duration = 400
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            efec1.alpha = animationValue
        }
    }
    private fun showDam() {
        dam.isVisible = true
        val animator = ValueAnimator.ofFloat(1f,0f)
        animator.duration = 400
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            dam.alpha = animationValue
        }
    }
    private fun downDam() {
        dam.isVisible = true
        val animator = ValueAnimator.ofFloat(0f,-50f)
        animator.duration = 400
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            dam.translationY = animationValue
        }
    }


    private fun upFeed() {
        val animator = ValueAnimator.ofFloat(1f,1.1f)
        animator.duration = 500
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            feed.scaleX = animationValue
            feed.scaleY = animationValue
        }
    }
    private fun downFeed() {
        val animator = ValueAnimator.ofFloat(1.1f,1f)
        animator.duration = 500
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            feed.scaleX = animationValue
            feed.scaleY = animationValue
        }
    }

    private fun inWorm() {
        val animator = ValueAnimator.ofFloat(0f,-200f)
        animator.duration = 2200
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            gfworm.translationX = animationValue
        }
    }

    private fun outFeed() {
        val animator = ValueAnimator.ofFloat(0f,-200f)
        animator.duration = 100
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            feed.translationY = animationValue
        }
    }
    private fun inFeed() {
        val animator = ValueAnimator.ofFloat(-200f,0f)
        animator.duration = 1500
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            feed.translationY = animationValue
        }
    }

}