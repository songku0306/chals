
package com.example.wormtycoon

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    data class Feed(val imageId: Int, val stage: Int, val perF: Int, val hp: Int)

    val feeds = mutableListOf(
            Feed(R.drawable.bean1, 1, 1, 10),
            Feed(R.drawable.ciz_init, 1, 1, 15),
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
    lateinit var tv_dam : TextView

    var gold = 0
    var num = 0
    var pow = currentWorm.power

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
        tv_dam = findViewById(R.id.tv_dam)

        tv_dam.isVisible = false
        tv_dam.text = "-" + pow.toString()

        outWorm()
        outFeed()
        Handler().postDelayed({
            inWorm()
            inFeed()
            touchUp()
        }, 1000)
    }

    fun touchUp() {
        tc_gold.setOnClickListener {
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
            iv_feed.setImageResource(newFeed.imageId)
        }

    }

    private fun effect() {
        upFeed()
        downFeed()
        showDam()
        downDam()
    }

    private fun showDam() {
        tv_dam.isVisible = true
        val animator = ValueAnimator.ofFloat(1f,0f)
        animator.duration = 500
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            tv_dam.alpha = animationValue
        }
    }
    private fun downDam() {
        tv_dam.isVisible = true
        val animator = ValueAnimator.ofFloat(0f,-100f)
        animator.duration = 600
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            tv_dam.translationY = animationValue
        }
    }

    private fun upFeed() {
        val animator = ValueAnimator.ofFloat(1f,1.1f)
        animator.duration = 500
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            iv_feed.scaleX = animationValue
            iv_feed.scaleY = animationValue
        }
    }
    private fun downFeed() {
        val animator = ValueAnimator.ofFloat(1.1f,1f)
        animator.duration = 500
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            iv_feed.scaleX = animationValue
            iv_feed.scaleY = animationValue
        }
    }

    private fun outWorm() {
        val animator = ValueAnimator.ofFloat(0f,200f)
        animator.duration = 100
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            iv_worm.translationX = animationValue
        }
    }
    private fun inWorm() {
        val animator = ValueAnimator.ofFloat(200f,0f)
        animator.duration = 900
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            iv_worm.translationX = animationValue
        }
    }

    private fun outFeed() {
        val animator = ValueAnimator.ofFloat(0f,-200f)
        animator.duration = 100
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            iv_feed.translationY = animationValue
        }
    }
    private fun inFeed() {
        val animator = ValueAnimator.ofFloat(-200f,0f)
        animator.duration = 900
        animator.start()
        animator.addUpdateListener { animation ->
            val animationValue = animation?.animatedValue as Float
            iv_feed.translationY = animationValue
        }
    }
}