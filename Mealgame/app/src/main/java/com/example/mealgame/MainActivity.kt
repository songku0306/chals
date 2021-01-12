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

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

//        var gold = 0

        var btn_menu: ImageButton = findViewById(R.id.menubtn)
//        var btn_status: ImageView = findViewById(R.id.statusbtn)
//        var lbl_gold: TextView = findViewById(R.id.lbl_gold)

        var btn_worm: ImageButton = findViewById(R.id.wobtn)
//        var btn_power: ImageButton = findViewById(R.id.pobtn)
//        var btn_level: ImageButton = findViewById(R.id.lvbtn)

        // =================메뉴칸====================
        btn_menu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

//        ===================버튼클릭======================
//        btn_worm.setOnClickListener {
//            gold -= 10
//        }
    }
}


