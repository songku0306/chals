package com.example.mylibrary

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.BookDatabase.Companion.getInstance


@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity() , OnDeleteListener {

    lateinit var db: BookDatabase
    var bookList: List<BookEntity> = listOf<BookEntity>()

    lateinit var badd: ImageButton
    lateinit var fbphase : EditText
    lateinit var blist : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        badd = findViewById(R.id.badd)
        fbphase = findViewById(R.id.fbphase)
        blist = findViewById(R.id.recyclerView)

        db = BookDatabase.getInstance(this)!!

        badd.setOnClickListener {
            var list = BookEntity(null, fbphase.text.toString())
            fbphase.setText("")
            insertList(list)
        }

        blist.layoutManager = LinearLayoutManager(this)

    }





    fun insertList(list: BookEntity) {
        val insertTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.listDAO().insert(list)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllList()
            }
        }
        insertTask.execute()
    }

    fun getAllList() {
        val getTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                bookList = db.listDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerView(bookList)
            }
        }).execute()
    }

    fun deleteList(list: BookEntity) {
        val deleteTask = object : AsyncTask<Unit,Unit,Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.listDAO().delete(list)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllList()
            }
        }
        deleteTask.execute()
    }

    fun setRecyclerView(bookList : List<BookEntity>) {
        blist.adapter = BookAdapter(this, bookList,this)
    }


    override fun onDeleteListener(list: BookEntity) {
        deleteList(list)
    }
}

