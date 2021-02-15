package com.example.mylibrary

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
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

    var imm : InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        badd = findViewById(R.id.badd)
        fbphase = findViewById(R.id.fbphase)
        blist = findViewById(R.id.recyclerView)

        db = BookDatabase.getInstance(this)!!

        badd.setOnClickListener {
            var list = BookEntity(null, fbphase.text.toString())
            fbphase.setText("")
            insertList(list)

        }
        imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager?

        blist.layoutManager = LinearLayoutManager(this)

    }



    fun hideKeyboard(v: View) {
        if(v != null) {
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
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

