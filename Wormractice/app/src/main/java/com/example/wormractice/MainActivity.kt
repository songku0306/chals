package com.example.wormractice

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

private lateinit var feed : ImageView
private lateinit var gif_worm: ImageView
private lateinit var start_rd : ImageButton
private lateinit var btn_power : ImageButton



@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity() {

    lateinit var db: WormDatabase
    var WormList: List<WormEntity> = listOf<WormEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feed = findViewById(R.id.feed)
        btn_power = findViewById(R.id.btn_power)
        start_rd = findViewById(R.id.start_rd)
        gif_worm = findViewById(R.id.gif_worm)

        db = WormDatabase.getInstance(this)!!

        feed.setImageResource(currentFeed.imageId)

        gif_worm.isVisible = false
        feed.isVisible = false

        start_rd.setOnClickListener {
            start_rd.isGone = true
            gif_worm.isVisible = true
            feed.isVisible = true

            gif_worm.animate().apply {
                duration = 2500
                translationX(-200f)
                start()
            }
        }

        btn_power.setOnClickListener {
            var worm = WormEntity(null, 1)
            insertList(worm)
        }

    }

    fun insertList(worm: WormEntity) {
        val insertTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.wormDAO().insert(worm)
            }
            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllList()
            }
        }).execute()
    }

    fun getAllList() {
        val getTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                WormList = db.wormDAO().getAll()
            }
            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                View.inflate(WormList)ImageView(WormList)
            }
        }).execute()
    }

    fun deleteList(worm: WormEntity) {
        val deleteTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.wormDAO().delete(worm)
            }
            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllList()
            }
        }).execute()
    }


}