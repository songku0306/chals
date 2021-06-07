package com.shopping.refrigeratorus.adapters

import android.content.Context
import android.graphics.Color.red
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shopping.refrigeratorus.R
import com.shopping.refrigeratorus.model.Refrigerator

class Adapters(var context: Context, var arrayList: ArrayList<Refrigerator>) :
    RecyclerView.Adapter<Adapters.ItemHolder>() {

    fun TextView.setTextColor(color: Long) = this.setTextColor(color.toInt())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.grid_layout_list_item, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val refrigerator: Refrigerator = arrayList.get(position)

        holder.icons.setImageResource(refrigerator.icons!!)
        holder.tv_days.text = refrigerator.count_days

        holder.icons.setOnClickListener {
            if (holder.tv_days == null) {
                object : CountDownTimer(30000, 1000 ) {
                override fun onTick(millisUntilFinished: Long) {
                    holder.tv_days.setText((millisUntilFinished /1000).toString())
                }
                override fun onFinish() {
                    holder.tv_days.setText("0")
                    holder.tv_days.setTextColor(0xff0000ff)
                }
            }.start()
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icons : ImageView = itemView.findViewById(R.id.iv_icons)
        var tv_days:TextView = itemView.findViewById(R.id.tv_days)
    }
}