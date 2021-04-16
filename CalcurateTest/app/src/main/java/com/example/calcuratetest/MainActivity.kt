package com.example.calcuratetest

import android.animation.ValueAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    data class Feed(val imageId: Int, val stage: Int, val per: Int, val hp: Int)
    val feeds = mutableListOf(
            Feed(R.drawable.bean1, 1, 1, 1),
            Feed(R.drawable.bean2, 1, 2, 10),
            Feed(R.drawable.bean3, 1, 3, 20),

            Feed(R.drawable.ciz1, 2, 30, 30),
            Feed(R.drawable.ciz2, 2, 5, 40),
            Feed(R.drawable.ciz3, 2, 6, 50)
    )
    private var currentFeed = feeds[0]

    data class Worm(val imageId: Int, val power: Int)
    val worms = mutableListOf(
            Worm(R.drawable.womo, 1),
            Worm(R.drawable.womo, 2)
    )
    private var currentWorm = worms[0]

    lateinit var tv1: TextView
    lateinit var touch: ConstraintLayout
    lateinit var feed: ImageView
    lateinit var worm: ImageView
    private lateinit var rd_start: Button

    var num : Int = 0
    var gold : Int = 0

    var pow = currentWorm.power
    var newFeed = feeds[0]


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
            priGold()
        }
        feed.setImageResource(currentFeed.imageId)
        worm.setImageResource(currentWorm.imageId)

        outWorm()
        outFeed()


        Handler().postDelayed( {
            inWorm()
            inFeed()
            touchUp()
        }, 1000)

    }

    private fun touchUp() {
        touch.setOnClickListener {
            num += pow
            gold += pow
            priGold()
            showCurrentFeed()
            effect()
        }
    }



    private fun effect() {
        upFeed()
        downFeed()
    }

    private fun showCurrentFeed() {
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

    private fun priGold() {
        if (gold <= 0) {
            Toast.makeText(this@MainActivity, "돈이 더 필요합니다", Toast.LENGTH_SHORT).show()
    } else {
            tv1.text = gold.toString()
        }
    }


    private fun outWorm() {
        val animator = ValueAnimator.ofFloat(0f,200f)
        animator.duration = 100
        animator.start()
        animator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animationValue = animation?.animatedValue as Float
                worm.translationX = animationValue
            }
        })
    }
    private fun inWorm() {
        val animator = ValueAnimator.ofFloat(200f,0f)
        animator.duration = 900
        animator.start()
        animator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animationValue = animation?.animatedValue as Float
                worm.translationX = animationValue
            }
        })
    }

    private fun outFeed() {
        val animator = ValueAnimator.ofFloat(0f,-200f)
        animator.duration = 100
        animator.start()
        animator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animationValue = animation?.animatedValue as Float
                feed.translationY = animationValue
            }
        })
    }
    private fun inFeed() {
        val animator = ValueAnimator.ofFloat(-200f,0f)
        animator.duration = 900
        animator.start()
        animator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animationValue = animation?.animatedValue as Float
                feed.translationY = animationValue
            }
        })
    }
    private fun upFeed() {
        val animator = ValueAnimator.ofFloat(1f,1.1f)
        animator.duration = 500
        animator.start()
        animator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animationValue = animation?.animatedValue as Float
                feed.scaleX = animationValue
                feed.scaleY = animationValue
            }
        })
    }
    private fun downFeed() {
        val animator = ValueAnimator.ofFloat(1.1f,1f)
        animator.duration = 500
        animator.start()
        animator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animationValue = animation?.animatedValue as Float
                feed.scaleX = animationValue
                feed.scaleY = animationValue
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
