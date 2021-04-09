package com.example.wormractice

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wormractice.WormDatabase.Companion.getInstance


@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity(), OnDeleteListener {

    lateinit var db: WormDatabase
    var WormList: List<WormEntity> = listOf<WormEntity>()

    lateinit var feed : ImageView
    lateinit var btn_power : ImageButton
    lateinit var wlist : RecyclerView



    lateinit var asdf : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feed = findViewById(R.id.feed)
        btn_power = findViewById(R.id.btn_power)
        wlist = findViewById(R.id.recyclerView)
        asdf = findViewById(R.id.tvtv)

        db = WormDatabase.getInstance(this)!!

        feed.setImageResource(currentFeed.imageId)


        btn_power.setOnClickListener {
            var list = WormEntity(null, asdf.text.toString())
            insertList(list)
        }

        wlist.layoutManager = LinearLayoutManager(this)

    }

    fun insertList(list: WormEntity) {
        val insertTask = (object : AsyncTask<Unit, Unit, Unit>() {
                    override fun doInBackground(vararg params: Unit?) {
                        db.wormDAO().insert(list)
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
                setRecyclerView(WormList)
            }
        }).execute()
    }

    fun deleteList(list: WormEntity) {
        val deleteTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.wormDAO().delete(list)
            }
            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllList()
            }
        }).execute()
    }

    fun setRecyclerView(wormList : List<WormEntity>) {
        wlist.adapter = WormAdapter(this, wormList,this)
    }


    override fun onDeleteListener(list: WormEntity) {
        deleteList(list)
    }
}
