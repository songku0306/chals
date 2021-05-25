package com.example.mealgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MenuActivity : AppCompatActivity() {
    lateinit var menudetail_menu : ImageView
    lateinit var back_menu: ConstraintLayout
    lateinit var adView_menu : AdView
    lateinit var btn_profile : Button
    lateinit var btn_backhome : Button
    lateinit var btn_shop : Button
    lateinit var tv_gold_menu : TextView
    lateinit var tv_dia_menu : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        menudetail_menu = findViewById(R.id.menudetail_menu)
        back_menu = findViewById(R.id.back_menu)
        adView_menu = findViewById(R.id.adView_menu)
        btn_profile = findViewById(R.id.btn_profile)
        btn_backhome = findViewById(R.id.btn_backhome)
        btn_shop = findViewById(R.id.btn_shop)
        tv_gold_menu = findViewById(R.id.tv_gold_menu)
        tv_dia_menu = findViewById(R.id.tv_dia_menu)

        if (intent.hasExtra("gold") && intent.hasExtra("ruby"))
        {
            val gold = intent.getStringExtra("gold")
            val ruby = intent.getStringExtra("ruby")

            tv_gold_menu.text = gold.toString()
            tv_dia_menu.text = ruby.toString()
        }


        back_menu.setOnClickListener {
            val gold = tv_gold_menu.toString()
            val ruby = tv_dia_menu.toString()
            val intent = Intent( this, MainActivity::class.java)
            intent.putExtra("gold", gold)
            intent.putExtra("ruby", ruby)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        btn_profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        btn_backhome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        btn_shop.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        val adView = AdView(this)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        MobileAds.initialize(this) {}
        AdView = findViewById(R.id.adView_menu)
        val adRequest = AdRequest.Builder().build()
        AdView.loadAd(adRequest)
    }

}