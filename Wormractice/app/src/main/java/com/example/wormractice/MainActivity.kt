package com.example.wormractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {


    lateinit var feed: ImageView
    lateinit var btn_power: ImageButton
    lateinit var cent : ConstraintLayout
    lateinit var asdf: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feed = findViewById(R.id.feed)
        btn_power = findViewById(R.id.btn_power)
        asdf = findViewById(R.id.asdf)
        cent = findViewById(R.id.center)
        var gold = 0
        var pow = 1
        cent.setOnClickListener {
            gold += pow
            asdf.text = gold.toString()
        }

        btn_power.setOnClickListener {
            setfrag(0)
        }

    }

    private fun setfrag(fragnum : Int) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragnum) {
        0 -> ft.replace(R.id.main_frame,MenuFragment()).commit()
            }

    }
}