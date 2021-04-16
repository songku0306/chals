package com.example.gemlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var iv : ImageView
    lateinit var gem_name : TextView
    lateinit var gem_search : EditText
    lateinit var btn_search : ImageButton
    lateinit var summary : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        iv = findViewById(R.id.iv)
        gem_name = findViewById(R.id.gem_name)
        gem_search = findViewById(R.id.gem_search)
        btn_search = findViewById(R.id.btn_search)
        summary = findViewById(R.id.summary)

    }

}