package com.example.goldtestt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.gms.ads.*
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var tv_gold : TextView
    lateinit var tv_seed : TextView

    lateinit var btn_reset : ImageButton
    lateinit var plus1 : ImageButton
    lateinit var plus2 : ImageButton
    lateinit var plus3 : ImageButton
    lateinit var plus4 : ImageButton
    lateinit var minus1 : ImageButton
    lateinit var minus2 : ImageButton
    lateinit var minus3 : ImageButton
    lateinit var minus4 : ImageButton

    lateinit var gold_1 : ImageButton
    lateinit var brown_1 : ImageButton
    lateinit var plat_1 : ImageButton

    var gold = 0
    var seed = 0

    lateinit var adbtn : ImageButton
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        tv_gold = findViewById(R.id.rhl)
        tv_seed = findViewById(R.id.tlem)

        btn_reset = findViewById(R.id.btn_reset)
        plus1 = findViewById(R.id.plus1)
        plus2 = findViewById(R.id.plus2)
        plus3 = findViewById(R.id.plus3)
        
        plus4 = findViewById(R.id.plus4)
        gold_1 = findViewById(R.id.gold_1)
        brown_1 = findViewById(R.id.brown_1)
        plat_1 = findViewById(R.id.plat_1)
        minus1 = findViewById(R.id.minus1)
        minus2 = findViewById(R.id.minus2)
        minus3 = findViewById(R.id.minus3)
        minus4 = findViewById(R.id.minus4)

        btn_reset.setOnClickListener {
            gold = 0
            tv_gold.text = gold.toString()
            seed = 0
            tv_seed.text = seed.toString()
        }

        plus1.setOnClickListener {
            gold += 1
            tv_gold.text = gold.toString()
            seed += 4400
            tv_seed.text = seed.toString()
        }

        plus2.setOnClickListener {
            gold += 5
            tv_gold.text = gold.toString()
            seed += 22000
            tv_seed.text = seed.toString()
        }

        plus3.setOnClickListener {
            gold += 10
            tv_gold.text = gold.toString()
            seed += 44000
            tv_seed.text = seed.toString()
        }
        plus4.setOnClickListener {
            gold += 50
            tv_gold.text = gold.toString()
            seed += 220000
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

        minus1.setOnClickListener {
            gold -= 1
            tv_gold.text = gold.toString()
            seed -= 4400
            tv_seed.text = seed.toString()
        }

        minus2.setOnClickListener {
            gold -= 5
            tv_gold.text = gold.toString()
            seed -= 22000
            tv_seed.text = seed.toString()
        }

        minus3.setOnClickListener {
            gold -= 10
            tv_gold.text = gold.toString()
            seed -= 44000
            tv_seed.text = seed.toString()
        }
        minus4.setOnClickListener {
            gold -= 50
            tv_gold.text = gold.toString()
            seed -= 220000
            tv_seed.text = seed.toString()
        }


        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/8691691433"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        adbtn = findViewById(R.id.adbtn)
        adbtn.setOnClickListener(View.OnClickListener {
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }
        })
    }



    override fun onDestroy() {
        super.onDestroy()
    }
}