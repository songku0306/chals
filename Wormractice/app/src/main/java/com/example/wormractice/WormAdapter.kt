package com.example.wormractice

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class WormAdapter(
    val context: Context,
    val worm : List<WormEntity>,
    val onDeleteListener : OnDeleteListener
    ) : RecyclerView.Adapter<WormAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val wlist : TextView
        val root : ConstraintLayout

        init {
            // Define click listener for the ViewHolder's View.
            wlist = itemView.findViewById(R.id.tvtv)
            root = itemView.findViewById(R.id.root)
        }
    }


    override fun getItemCount(): Int {
        return worm.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.mealworm_active,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = worm[position]

        holder.wlist.text = list.list
        holder.root.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                onDeleteListener.onDeleteListener(list)
                return true
            }
        })
    }




}