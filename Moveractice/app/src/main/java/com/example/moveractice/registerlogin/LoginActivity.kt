package com.example.moveractice.registerlogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moveractice.R
import com.example.moveractice.messages.LatestMessagesActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity: AppCompatActivity() {
    lateinit var email_edittext_register2 : EditText
    lateinit var password_edittext_register2 : EditText
    lateinit var login_button_register : Button
    lateinit var back_to_registeration_textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email_edittext_register2 =findViewById(R.id.email_edittext_register2)
        password_edittext_register2 = findViewById(R.id.password_edittext_register2)
        login_button_register = findViewById(R.id.login_button_register)
        back_to_registeration_textView = findViewById(R.id.back_to_registeration_textView)

        login_button_register.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        val email = email_edittext_register2.text.toString()
        val password = password_edittext_register2.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("LoginActivity", "Email is: " + email)
        Log.d("LoginActivity", "Password: $password")

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful)  return@addOnCompleteListener
                val intent = Intent(this, LatestMessagesActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                Log.d("LoginActivity", "Successfully loged in")
            }
            .addOnFailureListener {
                Log.d("LoginActivity", "Failed to log in")
            }

    }
}