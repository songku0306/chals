package com.example.moveractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewMessageActivity : AppCompatActivity() {
    lateinit var recyclerview_newmessage : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        recyclerview_newmessage = findViewById(R.id.recyclerview_newmessage)

        supportActionBar?.title = "Select User"

    }
}

