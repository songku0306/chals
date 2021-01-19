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
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import pl.droidsonroids.gif.GifImageView
import java.lang.Exception
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private lateinit var lbl_gold: TextView
    private lateinit var btn_worm: ImageButton
    private lateinit var btn_menu: ImageButton
    private lateinit var rd_start: Button
    private lateinit var ob_ciz: ImageView
    private lateinit var gif_worm: GifImageView
    private lateinit var btn_level: ImageButton
    private lateinit var egg: ImageView

    //        var btn_status: ImageView = findViewById(R.id.statusbtn)
//        var btn_power: ImageButton = findViewById(R.id.pobtn)
    private var gold = 0L
    private var timerTask: Timer? = null
    lateinit var AdView : AdView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        lbl_gold = findViewById(R.id.lbl_gold)
        btn_menu = findViewById(R.id.menubtn)
        btn_worm= findViewById(R.id.wobtn)
        rd_start= findViewById(R.id.rd_start)
        ob_ciz= findViewById(R.id.gif_ciz)
        gif_worm= findViewById(R.id.gif_worm)
        btn_level = findViewById(R.id.lvbtn)
        egg = findViewById(R.id.egg)

        // =================메뉴칸======================================================
        btn_menu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        gif_worm.isVisible = false

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
                duration = 1500
                translationX(-200f)
                start()
            }
        }
//        ===================버튼클릭================================================
        btn_worm.setOnClickListener {
            gold -= 10
        }
        val animations = arrayOf(-140f).map { translation ->
            ObjectAnimator.ofFloat(egg, "translationX", translation).apply {
                duration = 800
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.RESTART
            }
        }
        btn_level.setOnClickListener {
            egg.animate().apply {
                duration = 100
                scaleX(1f)
                scaleY(1f)
                start()
            }

        }
        onGold()
        val adView = AdView(this)

        adView.adSize = AdSize.BANNER

        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
// TODO: Add adView to your view hierarchy.

        MobileAds.initialize(this) {}
        AdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        AdView.loadAd(adRequest)
    }

    private fun onGold() {
        timerTask = timer(period=2000, initialDelay = 300) {
            gold++
            runOnUiThread {
                lbl_gold?.text = "$gold"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        offTimer()
    }

    private fun offTimer() {
        timerTask?.cancel()
    }
}