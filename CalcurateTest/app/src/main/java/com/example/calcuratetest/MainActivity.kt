package com.example.calcuratetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var btn1 : Button
    lateinit var tv1 : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)
        tv1 = findViewById(R.id.tv1)

        var tv1 = 0


        btn1.setOnClickListener {
            tv1 += 1
        }
    }
}