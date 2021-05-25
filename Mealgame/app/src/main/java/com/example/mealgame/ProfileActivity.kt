package com.example.mealgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class ProfileActivity : AppCompatActivity() {
    lateinit var adView_profile : AdView
    lateinit var btn_back_tomenu : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)


        adView_profile = findViewById(R.id.adView_profile)
        btn_back_tomenu = findViewById(R.id.btn_back_tomenu)

        btn_back_tomenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        val adView = com.google.android.gms.ads.AdView(this)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        MobileAds.initialize(this) {}
        AdView = findViewById(R.id.adView_profile)
        val adRequest = AdRequest.Builder().build()
        AdView.loadAd(adRequest)
    }
}