package com.example.goldtestt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var tv_gold : TextView
    lateinit var tv_seed : TextView

    lateinit var btn_reset : Button
    lateinit var gold_1 : Button
    lateinit var brown_1 : Button
    lateinit var plat_1 : Button
    lateinit var sd_44 : Button
    lateinit var sd_22 : Button
    lateinit var sd_444 : Button

    var gold = 0
    var seed = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_gold = findViewById(R.id.rhl)
        tv_seed = findViewById(R.id.tlem)

        btn_reset = findViewById(R.id.btn_reset)
        gold_1 = findViewById(R.id.gold_1)
        brown_1 = findViewById(R.id.brown_1)
        plat_1 = findViewById(R.id.plat_1)
        sd_44 = findViewById(R.id.sd_44)
        sd_22 = findViewById(R.id.sd_22)
        sd_444 = findViewById(R.id.sd_444)


        btn_reset.setOnClickListener {
            gold = 0
            tv_gold.text = gold.toString()
            seed = 0
            tv_seed.text = seed.toString()
        }

        gold_1.setOnClickListener {
            gold += 1
            tv_gold.text = gold.toString()
            seed += 4400
            tv_seed.text = seed.toString()
        }

        brown_1.setOnClickListener {
            gold += 5
            tv_gold.text = gold.toString()
            seed += 22000
            tv_seed.text = seed.toString()
        }

        plat_1.setOnClickListener {
            gold += 10
            tv_gold.text = gold.toString()
            seed += 44000
            tv_seed.text = seed.toString()
        }

        sd_44.setOnClickListener {
            gold += 1
            tv_gold.text = gold.toString()
            seed += 4400
            tv_seed.text = seed.toString()
        }

        sd_22.setOnClickListener {
            gold += 5
            tv_gold.text = gold.toString()
            seed += 22000
            tv_seed.text = seed.toString()
        }

        sd_444.setOnClickListener {
            gold += 10
            tv_gold.text = gold.toString()
            seed += 44000
            tv_seed.text = seed.toString()
        }
    }







    override fun onDestroy() {
        super.onDestroy()
    }
}