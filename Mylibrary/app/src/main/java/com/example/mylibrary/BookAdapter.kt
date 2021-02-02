package com.example.mylibrary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(
        val context : Context,
        val list : List<BookEntity>,
        val onDeleteListener: OnDeleteListener
        ) : RecyclerView.Adapter<BookAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val blist : TextView
        val root : ConstraintLayout

        init {
            // Define click listener for the ViewHolder's View.
            blist = itemView.findViewById(R.id.book_summary)
            root = itemView.findViewById(R.id.root)
        }

    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.book_list,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = list[position]

        holder.blist.text = list.list
        holder.root.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                onDeleteListener.onDeleteListener(list)
                return true
            }
        })
    }




}