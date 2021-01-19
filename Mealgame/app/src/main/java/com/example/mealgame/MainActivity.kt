package com.example.mealgame

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
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.lang.Exception
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private lateinit var lbl_gold: TextView
    private lateinit var btn_worm: ImageButton
    private lateinit var btn_menu: ImageButton
    private lateinit var rd_start: Button
//        var btn_status: ImageView = findViewById(R.id.statusbtn)
//        var btn_power: ImageButton = findViewById(R.id.pobtn)
//        var btn_level: ImageButton = findViewById(R.id.lvbtn)
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

        // =================메뉴칸====================
        btn_menu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        rd_start.setOnClickListener {
            rd_start.isGone = true

        }
//        ===================버튼클릭======================
        btn_worm.setOnClickListener {
            gold -= 10

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