package com.example.goldtestt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var tv_gold : TextView
    lateinit var tv_seed : TextView

    lateinit var gl1 : Button
    lateinit var gl10 : Button
    lateinit var gl100 : Button
    lateinit var sd1000 : Button
    lateinit var sd5000 : Button
    lateinit var sd101 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_gold = findViewById(R.id.tv_gold)
        tv_seed = findViewById(R.id.tv_seed)

        var tv_seed : Int = 0
        var tv_gold : Int = 0

        gl1 = findViewById(R.id.gl1)
        gl10 = findViewById(R.id.brown_1)
        gl100 = findViewById(R.id.plat_1)
        sd1000 = findViewById(R.id.sd1000)
        sd5000 = findViewById(R.id.sd5000)
        sd101 = findViewById(R.id.sd101)


        gl1.setOnClickListener {
            tv_gold = tv_gold.plus(1)
            tv_seed = tv_seed.plus(4400)
        }
    }







    override fun onDestroy() {
        super.onDestroy()
    }
}