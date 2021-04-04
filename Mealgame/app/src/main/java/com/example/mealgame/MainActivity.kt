package com.example.mealgame

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import pl.droidsonroids.gif.GifImageView
import java.lang.Exception
import java.util.*
import kotlin.concurrent.timer
//import androidx.databinding.DataBindingUtil



class MainActivity : AppCompatActivity() {


    private lateinit var midlay : ConstraintLayout
    private lateinit var rd_start: Button


    private lateinit var ob_ciz: ImageView
    private lateinit var gif_worm: GifImageView
    private lateinit var lbl_gold: TextView

    //        var btn_status: ImageView = findViewById(R.id.statusbtn)
//        var btn_power: ImageButton = findViewById(R.id.pobtn)

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

            Feed(R.drawable.gif_ciz, 2, 1, 100),
            Feed(R.drawable.tuna, 1, 3, 2500)
    )

    private var currentFeed = feeds[0]


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        midlay = findViewById(R.id.midlay)
        rd_start = findViewById(R.id.rd_start)
        ob_ciz = findViewById(R.id.gif_ciz)
        gif_worm = findViewById(R.id.gif_worm)

        lbl_gold = findViewById(R.id.lbl_gold)

        var gold = 0
// =================메뉴칸======================================================


        ob_ciz.setImageResource(currentFeed.imageId)

        //-------------------라운드 시작---------------------------------------------------
        rd_start.setOnClickListener {
            rd_start.isGone = true
            ob_ciz.isVisible = true

            ob_ciz.animate().apply {
                duration = 1000
                translationY(300f)
            }.withEndAction {
                ob_ciz.animate().apply {
                    duration = 100
                    scaleX(1.05f)
                }.withEndAction {
                    ob_ciz.animate().apply {
                        startDelay = 100
                        duration = 100
                        scaleX(0.95f)
                    }.withEndAction {
                        ob_ciz.animate().apply {
                            duration = 100
                            scaleX(1.05f)
                            scaleY(1.05f)
                        }.withEndAction {
                            ob_ciz.animate().apply {
                                startDelay = 100
                                duration = 100
                                scaleX(0.95f)
                                scaleY(0.95f)
                            }.start()
                        }
                    }
                }
            }


            gif_worm.isVisible = true
            gif_worm.animate().apply {
                duration = 2500
                translationX(-200f)
                start()
            }
        }

//===================버튼클릭===========================================================================================================

        midlay.setOnClickListener {
            gold += 1
            lbl_gold.text = "$gold"
        }




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

    override fun onDestroy() {
        super.onDestroy()
    }
}
