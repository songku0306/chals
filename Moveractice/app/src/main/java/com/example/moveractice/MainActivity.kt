package com.example.moveractice

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit var touch:ConstraintLayout
    lateinit var worm: ImageView
    lateinit var feed:ImageView
    lateinit var pow: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        feed = findViewById(R.id.feed)
        worm = findViewById(R.id.worm)
        touch = findViewById(R.id.touch)
        pow = findViewById(R.id.pow)

        var currentPower = 1
        var poow = currentPower
        pow.text = "-" + poow.toString()
        pow.isVisible = false
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
        effect()
        }
    }
    private fun effect() {
        upFeed()
        downFeed()
        showDam()
        downDam()
    }

    private fun showDam() {
        pow.isVisible = true
        val animator = ValueAnimator.ofFloat(1f,0f)
        animator.duration = 400
        animator.start()
        animator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animationValue = animation?.animatedValue as Float
                pow.alpha = animationValue
            }
        })
    }
    private fun downDam() {
        pow.isVisible = true
        val animator = ValueAnimator.ofFloat(0f,100f)
        animator.duration = 400
        animator.start()
        animator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animationValue = animation?.animatedValue as Float
                pow.translationY = animationValue
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