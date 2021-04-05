package com.example.mealgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import pl.droidsonroids.gif.GifImageView


private lateinit var gif_worm: GifImageView


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        gif_worm = findViewById(R.id.worm)


        gif_worm.animate().apply {
            duration = 2000
            rotation(720f)
        }.withEndAction {
            gif_worm.animate().apply {
                duration = 3000
                scaleX(0f)
                scaleY(0f)
            }.start()
    }



        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
            finish()
        },2000)
    }
}