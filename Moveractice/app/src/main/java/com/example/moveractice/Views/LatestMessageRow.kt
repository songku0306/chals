package com.example.moveractice.Views

import androidx.recyclerview.widget.RecyclerView
import com.example.moveractice.R
import com.example.moveractice.models.ChatMessage
import com.example.moveractice.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.latest_msg_row.view.*

class LatestMessageRow(val chatMessage: ChatMessage): Item<RecyclerView.ViewHolder>(){
    var chatPartnerUser : User? = null

    override fun bind(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder.itemView.message_textview_latest.text = chatMessage.text
        val chatPartnerId : String
        if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
            chatPartnerId = chatMessage.toId
        }else {
            chatPartnerId = chatMessage.fromId
        }
        val ref = FirebaseDatabase.getInstance().getReference("/users/$chatPartnerId")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                chatPartnerUser = p0.getValue(User::class.java)
                viewHolder.itemView.username_textview_latest.text = chatPartnerUser?.username
                val targetImageView = viewHolder.itemView.imageview_latest
                Picasso.get().load(chatPartnerUser?.profileImageUrl).into(targetImageView)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
    override fun getLayout(): Int {
        return R.layout.latest_msg_row
    }
}