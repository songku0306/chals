package com.example.moveractice

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.HandlerCompat.postDelayed

class MainActivity : AppCompatActivity() {
    lateinit var touch:ConstraintLayout
    lateinit var worm: ImageView
    lateinit var feed:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        feed = findViewById(R.id.feed)
        worm = findViewById(R.id.worm)
        touch = findViewById(R.id.touch)

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

}