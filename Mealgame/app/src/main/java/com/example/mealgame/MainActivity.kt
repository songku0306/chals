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
import android.widget.Button
import androidx.annotation.RequiresApi
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

    private lateinit var ViewModel: Gold

//    private lateinit var binding: ActivityMainBinding


    private lateinit var btn_worm: ImageButton
    private lateinit var btn_menu: ImageButton
    private lateinit var rd_start: Button
    private lateinit var ob_ciz: ImageView
    private lateinit var gif_worm: GifImageView
    private lateinit var btn_level: ImageButton
    private lateinit var egg: ImageView
    private lateinit var lbl_gold: TextView

    //        var btn_status: ImageView = findViewById(R.id.statusbtn)
//        var btn_power: ImageButton = findViewById(R.id.pobtn)

    lateinit var AdView: AdView


    data class Feed(val imageId: Int, val stage: Int, val round: Int, val healthPoint: Int)

    val feeds = mutableListOf(
            Feed(R.drawable.beans, 1, 1, 100),
            Feed(R.drawable.gif_ciz, 1, 2, 500),
            Feed(R.drawable.tuna, 1, 3, 2500)
    )

    private var currentFeed = feeds[0]


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        btn_menu = findViewById(R.id.menubtn)
        btn_worm = findViewById(R.id.wobtn)
        rd_start = findViewById(R.id.rd_start)
        ob_ciz = findViewById(R.id.gif_ciz)
        gif_worm = findViewById(R.id.gif_worm)
        btn_level = findViewById(R.id.lvbtn)
        egg = findViewById(R.id.egg)
        lbl_gold = findViewById(R.id.lbl_gold)

// =================메뉴칸======================================================
        btn_menu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        gif_worm.isVisible = false
        ob_ciz.setImageResource(currentFeed.imageId)
        //-------------------라운드 시작---------------------------------------------------
        rd_start.setOnClickListener {
            rd_start.isGone = true

            ob_ciz.animate().apply {
                duration = 1500
                translationY(300f)
                start()
            }

            gif_worm.isVisible = true

            gif_worm.animate().apply {
                duration = 2500
                translationX(-100f)
                start()
            }

        }
//===================버튼클릭================================================
        btn_worm.setOnClickListener {
            ViewModel._gold.value = (ViewModel._gold.value)?.minus(10)
        }


        btn_level.setOnClickListener {
            egg.animate().apply {
                duration = 1000
                scaleXBy(0.07f)
                scaleYBy(0.07f)
                start()
            }

        }


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



