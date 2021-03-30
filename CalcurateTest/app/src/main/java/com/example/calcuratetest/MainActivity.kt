package com.example.calcuratetest

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    lateinit var btn1 : Button
    lateinit var tv1 : TextView

    var num = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)
        tv1 = findViewById(R.id.tv1)



        btn1.setOnClickListener {
            num += 1
            tv1.text = "$num"
        }

    }
}