package com.example.calcuratetest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FrozenAdapter(val context: Context) : RecyclerView.Adapter<FrozenAdapter.Viewholder>() {
    var dataList = emptyList<Frozen>()

    internal fun setDataList(dataList : List<Frozen>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }
    class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var poto : ImageView
        var tv_day : TextView
        init {
            poto = itemView.findViewById(R.id.iv_food)
            tv_day = itemView.findViewById(R.id.tv_day1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrozenAdapter.Viewholder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.items_layout, parent, false)
        return Viewholder(view)
    }
    override fun getItemCount() = dataList.size
    override fun onBindViewHolder(holder: FrozenAdapter.Viewholder, position: Int) {
        var data = dataList[position]

        holder.poto.setImageResource(data.pictureFood)
        holder.tv_day.text = data.time

    }


}