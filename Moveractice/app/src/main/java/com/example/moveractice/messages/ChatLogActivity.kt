package com.example.moveractice.messages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.moveractice.NewMessageActivity
import com.example.moveractice.R
import com.example.moveractice.Views.ChatFromItem
import com.example.moveractice.Views.ChatToItem
import com.example.moveractice.models.ChatMessage
import com.example.moveractice.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieAdapter
import kotlinx.android.synthetic.main.activity_chat_log.*

class ChatLogActivity : AppCompatActivity() {
    companion object {
        val TAG = "ChatLog"
    }

    val adapter = GroupieAdapter()

    var toUser : User? = null

    lateinit var recyclerview_chat_log : RecyclerView
    lateinit var send_button_chat_log : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)
        recyclerview_chat_log = findViewById(R.id.recyclerview_chat_log)
        send_button_chat_log = findViewById(R.id.send_button_chat_log)

        recyclerview_chat_log.adapter = adapter

        toUser = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)

        supportActionBar?.title = toUser?.username

        listenForMsgs()
        send_button_chat_log.setOnClickListener {
            Log.d(TAG, "Attempt to send msg..")
            performSendMsg()
        }

    }

    private fun listenForMsgs() {
        val fromId = FirebaseAuth.getInstance().uid
        val toId = toUser?.uid
        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")
        ref.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(p0: DataSnapshot, previousChildName: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)
                if (chatMessage != null) {
                    Log.d(TAG, chatMessage?.text)
                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                        val currentUser = LatestMessagesActivity.currentUser ?: return
                        adapter.add(ChatFromItem(chatMessage.text, currentUser))
                    } else {
                        adapter.add(ChatToItem(chatMessage.text, toUser!!))
                    }
                }
                recyclerview_chat_log.scrollToPosition(adapter.getItemCount() - 1)
            }

            override fun onCancelled(error: DatabaseError) {

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }
        })
    }



    private fun performSendMsg() {
        val text = edittext_chat_log.text.toString()

        val fromId = FirebaseAuth.getInstance().uid
        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        val toId = user!!.uid
        if (fromId == null) return

//        val ref = FirebaseDatabase.getInstance().getReference("/messages").push()
        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()
        val toref = FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()


        val chatMessage = ChatMessage(ref.key!!, text, fromId, toId, System.currentTimeMillis() / 1000)

        ref.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d(TAG, "Saved our chat msg:${ref.key}")
                edittext_chat_log.text.clear()
                recyclerview_chat_log.scrollToPosition(adapter.getItemCount() - 1)
            }
        toref.setValue(chatMessage)

        val latestMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId/$toId")

        latestMessageRef.setValue(chatMessage)
        val latestToMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$toId/$fromId")
        latestToMessageRef.setValue(chatMessage)

    }
}
