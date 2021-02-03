package com.example.wormractice

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView

class WormAdapter(
    var context: Context,
    var worm : List<WormEntity>
    ) : ItemAdapter {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val worm : WormEntity = worm[position]
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val wormView : View = inflater.inflate(R.layout.activity_main, null)
            wormView.imageView.setBackGroundResource(worm.image!!)


    }
    return WormView
}

interface ItemAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}

