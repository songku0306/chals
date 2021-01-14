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
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private lateinit var lbl_gold: TextView
    private lateinit var btn_worm: ImageButton
    private lateinit var btn_menu: ImageButton
//        var btn_status: ImageView = findViewById(R.id.statusbtn)
//        var btn_power: ImageButton = findViewById(R.id.pobtn)
//        var btn_level: ImageButton = findViewById(R.id.lvbtn)
    private var gold = 0L
    private var timerTask: Timer? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        lbl_gold = findViewById(R.id.lbl_gold)
        btn_menu = findViewById(R.id.menubtn)
        btn_worm= findViewById(R.id.wobtn)

        // =================메뉴칸====================
        btn_menu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

//        ===================버튼클릭======================
        btn_worm.setOnClickListener {
            gold -= 10
        }
        onGold()
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