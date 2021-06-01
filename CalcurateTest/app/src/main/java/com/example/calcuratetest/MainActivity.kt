package com.example.calcuratetest


import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.room.Room

@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity() {

    lateinit var db: FrozenDatabase

    var timeList: List<Frozen> = listOf<Frozen>()

    lateinit var item1 : ConstraintLayout
    lateinit var item2 : ConstraintLayout
    lateinit var item3 : ConstraintLayout
    lateinit var item4 : ConstraintLayout
    lateinit var item5 : ConstraintLayout
    lateinit var item6 : ConstraintLayout
    lateinit var item7 : ConstraintLayout
    lateinit var item8 : ConstraintLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = FrozenDatabase.getInstance(this)!!

        item1 = findViewById(R.id.item1)
        item2 = findViewById(R.id.item2)
        item3 = findViewById(R.id.item3)
        item4 = findViewById(R.id.item4)
        item5 = findViewById(R.id.item5)
        item6 = findViewById(R.id.item6)
        item7 = findViewById(R.id.item7)
        item8 = findViewById(R.id.item8)
        setFrozon()

        teaShom()

    }

    private fun teaShom() {
        println()
    }

    private fun setFrozon() {
        val tv_day1: TextView = findViewById(R.id.tv_day1)
        val tv_day2: TextView = findViewById(R.id.tv_day2)
        val tv_day3: TextView = findViewById(R.id.tv_day3)
        val tv_day4: TextView = findViewById(R.id.tv_day4)
        val tv_day5: TextView = findViewById(R.id.tv_day5)
        val tv_day6: TextView = findViewById(R.id.tv_day6)
        val tv_day7: TextView = findViewById(R.id.tv_day7)
        val tv_day8: TextView = findViewById(R.id.tv_day8)

        item1.setOnClickListener {

            object : CountDownTimer(300000, 10000 ) {
                        override fun onTick(millisUntilFinished: Long) {
                            tv_day1.setText((millisUntilFinished /10000).toString())
                            Log.d("time", "10초")
                        }
                        override fun onFinish() {
                            tv_day1.setText("0")
                            tv_day1.setTextColor(ContextCompat.getColor(baseContext, R.color.red))
                }
            }.start()

            val time = Frozen(null, tv_day1.text.toString())
            insertTimeList(time)

        }
        item2.setOnClickListener { tv_day2.text = 15.toString() }
        item3.setOnClickListener { tv_day3.text = 23.toString() }
        item4.setOnClickListener { tv_day4.text = 5.toString() }
        item5.setOnClickListener { tv_day5.text = 12.toString() }
        item6.setOnClickListener { tv_day6.text = 54.toString() }
        item7.setOnClickListener { tv_day7.text = 23.toString() }
        item8.setOnClickListener { tv_day8.text = 54.toString() }
    }


    fun insertTimeList(time: Frozen) {
        val insertTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.timeDAO().insert(time)
                Log.d("time", "server on")

            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllList()
            }
        }
        insertTask.execute()
    }

    fun getAllList() {
        val getTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                timeList = db.timeDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
            }
        }
        getTask.execute()
    }


}
