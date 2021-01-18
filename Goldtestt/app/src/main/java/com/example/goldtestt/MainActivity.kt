package com.example.goldtestt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.util.*
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {

    private lateinit var lbl_gold: TextView
    private lateinit var livegold: TextView
    private lateinit var btn_worm: ImageButton

    private var gold = 0
    private var timerTask: Timer? = null
    lateinit var AdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lbl_gold = findViewById(R.id.lbl_gold)
        btn_worm = findViewById(R.id.button)
        livegold = findViewById(R.id.lbl_gold)

        startTimer()

        btn_worm.isEnabled = ( livegold >= 10 )


        btn_worm.setOnClickListener {
            gold -= 10
        }

        val adView = AdView(this)
        val adSize = AdSize(300, 50)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-3940256099942544~3347511713"
        MobileAds.initialize(this) {}
        AdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        AdView.loadAd(adRequest)


    }

    private fun startTimer(): TextView {
        timerTask = timer(period=500) {
            gold++
            runOnUiThread {
                lbl_gold?.text = "$gold"
            }
        }
        return livegold
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    private fun stopTimer() {
        timerTask?.cancel()
    }
}
