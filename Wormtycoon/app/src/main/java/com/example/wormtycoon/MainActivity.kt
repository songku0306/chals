package com.example.wormtycoon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

class MainActivity : AppCompatActivity() {

    data class Feed(val imageId: Int, val stage: Int, val per: Int, val hp: Int)

    val feeds = mutableListOf(
            Feed(R.drawable.bean1, 1, 1, 5),
            Feed(R.drawable.bean2, 1, 2, 10),
            Feed(R.drawable.bean3, 1, 3, 20),
            Feed(R.drawable.bean4, 1, 4, 30),
            Feed(R.drawable.bean5, 1, 5, 40),
            Feed(R.drawable.bean6, 1, 6, 50),
            Feed(R.drawable.bean7, 1, 7, 60),
            Feed(R.drawable.bean8, 1, 8, 70),
            Feed(R.drawable.bean9, 1, 9, 80),
            Feed(R.drawable.bean10, 1, 10, 90)
    )

    private var currentFeed = feeds[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)


    }


}