package com.example.moveractice.Views

import androidx.recyclerview.widget.RecyclerView
import com.example.moveractice.R
import com.example.moveractice.models.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*


class ChatFromItem(val text: String, val user : User): Item<RecyclerView.ViewHolder>() {
    override fun bind(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder.itemView.textview_from_row.text = text
        val uri = user.profileImageUrl
        val targetImageView = viewHolder.itemView.imageView_from_row
        Picasso.get().load(uri).into(targetImageView)
    }

    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }

}

class ChatToItem(val text: String, val user: User): Item<RecyclerView.ViewHolder>() {
    override fun bind(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder.itemView.textview_to_row.text = text
        val uri = user.profileImageUrl
        val targetImageView = viewHolder.itemView.imageView_to_row
        Picasso.get().load(uri).into(targetImageView)
    }

    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }

}