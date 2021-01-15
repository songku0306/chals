package com.example.goldtestt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.util.*
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {

    private lateinit var lbl_gold: TextView

    private var gold = 0
    private var timerTask: Timer? = null
    lateinit var AdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lbl_gold = findViewById(R.id.lbl_gold)

        startTimer()

        val adView = AdView(this)
        val adSize = AdSize(300, 50)

        adView.adSize = AdSize.BANNER

        adView.adUnitId = "ca-app-pub-3940256099942544~3347511713"
// TODO: Add adView to your view hierarchy.

        MobileAds.initialize(this) {}

        AdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        AdView.loadAd(adRequest)
    }

    private fun startTimer() {
        timerTask = timer(period=1000) {
            gold++
            runOnUiThread {
                lbl_gold?.text = "$gold"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    private fun stopTimer() {
        timerTask?.cancel()
    }
}