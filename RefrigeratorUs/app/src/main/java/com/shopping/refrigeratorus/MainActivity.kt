package com.shopping.refrigeratorus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shopping.refrigeratorus.adapters.Adapters
import com.shopping.refrigeratorus.model.Refrigerator


class MainActivity : AppCompatActivity() {

    private var frozen_container:RecyclerView ? = null
    private var cool_container:RecyclerView ? = null
    private var gridLayoutManager:GridLayoutManager ? = null
    private var arrayList:ArrayList<Refrigerator> ? = null
    private var adapters:Adapters ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setFrozen()
        setCool()

        clickFood()

    }

    private fun clickFood() {
        TODO("Not yet implemented")
    }


    private fun setFrozen() {
        frozen_container = findViewById(R.id.frozen_container)
        gridLayoutManager = GridLayoutManager(applicationContext, 4, LinearLayoutManager.VERTICAL, false)
        frozen_container?.layoutManager = gridLayoutManager
        frozen_container?.setHasFixedSize(false)
        arrayList = ArrayList()
        arrayList = setDataFrozenList()
        adapters = Adapters(applicationContext, arrayList!!)
        frozen_container?.adapter = adapters

    }
    private fun setCool() {
        cool_container = findViewById(R.id.cool_container)
        gridLayoutManager = GridLayoutManager(applicationContext, 4, LinearLayoutManager.VERTICAL, false)
        cool_container?.layoutManager = gridLayoutManager
        cool_container?.setHasFixedSize(true)
        arrayList = ArrayList()
        arrayList = setDataCoolList()
        adapters = Adapters(applicationContext, arrayList!!)
        cool_container?.adapter = adapters

    }

    private fun setDataFrozenList() : ArrayList<Refrigerator> {
        var items:ArrayList<Refrigerator> = ArrayList()


        items.add(Refrigerator(R.drawable.steak, "30"))
        items.add(Refrigerator(R.drawable.bacon, "30"))
        items.add(Refrigerator(R.drawable.chicken, "30"))
        items.add(Refrigerator(R.drawable.fish, "60"))

        items.add(Refrigerator(R.drawable.dumpling, "60"))
        items.add(Refrigerator(R.drawable.mochi, "30"))
        items.add(Refrigerator(R.drawable.pizza, "30"))

    return items
}
    private fun setDataCoolList() : ArrayList<Refrigerator> {
        var items:ArrayList<Refrigerator> = ArrayList()


        items.add(Refrigerator(R.drawable.chicken, "3"))
        items.add(Refrigerator(R.drawable.steak, "2"))
        items.add(Refrigerator(R.drawable.bacon, "7"))
        items.add(Refrigerator(R.drawable.fish, "5"))

        items.add(Refrigerator(R.drawable.pizza, "3"))
        items.add(Refrigerator(R.drawable.bread, "3"))
        items.add(Refrigerator(R.drawable.cupcake, "2"))
        items.add(Refrigerator(R.drawable.mochi, "2"))

        items.add(Refrigerator(R.drawable.beverage, "10"))
        items.add(Refrigerator(R.drawable.milk, "10"))
        items.add(Refrigerator(R.drawable.cheese, "30"))
        items.add(Refrigerator(R.drawable.rice, "30"))

        items.add(Refrigerator(R.drawable.tomato, "4"))
        items.add(Refrigerator(R.drawable.cabbage, "4"))
        items.add(Refrigerator(R.drawable.carrot, "4"))
        items.add(Refrigerator(R.drawable.pepper, "4"))

        return items
    }
}