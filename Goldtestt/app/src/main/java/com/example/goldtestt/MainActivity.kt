package com.example.goldtestt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var tv_gold : TextView
    lateinit var tv_seed : TextView

    lateinit var silver_1 : Button
    lateinit var gold_1 : Button
    lateinit var brown_1 : Button
    lateinit var plat_1 : Button
    lateinit var sd_11 : Button
    lateinit var sd_44 : Button
    lateinit var sd_22 : Button
    lateinit var sd_444 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_gold = findViewById(R.id.tv_gold)
        tv_seed = findViewById(R.id.tv_seed)

        var tv_seed : Int = 0
        var tv_gold : Int = 0

        silver_1 = findViewById(R.id.silver_1)
        gold_1 = findViewById(R.id.gold_1)
        brown_1 = findViewById(R.id.brown_1)
        plat_1 = findViewById(R.id.plat_1)
        sd_11 = findViewById(R.id.sd_11)
        sd_44 = findViewById(R.id.sd_44)
        sd_22 = findViewById(R.id.sd_22)
        sd_444 = findViewById(R.id.sd_444)



    }







    override fun onDestroy() {
        super.onDestroy()
    }
}