package com.example.moveractice

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class RegisterActivity : AppCompatActivity() {

    companion object {
        val TAG = "RegisterActivity"
    }

    lateinit var username_edittext_register : EditText
    lateinit var email_edittext_register : EditText
    lateinit var password_edittext_register : EditText
    lateinit var register_button_register : Button
    lateinit var already_have_account_textView : TextView
    lateinit var select_photo_register : ImageButton
    lateinit var select_photo_imageview_register : CircleImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username_edittext_register  = findViewById(R.id.username_edittext_register)
        email_edittext_register  = findViewById(R.id.email_edittext_register)
        password_edittext_register = findViewById(R.id.password_edittext_register)
        register_button_register = findViewById(R.id.register_button_register)
        already_have_account_textView = findViewById(R.id.already_have_account_textView)
        select_photo_register = findViewById(R.id.select_photo_register)
        select_photo_imageview_register = findViewById(R.id.select_photo_imageview_register)

        register_button_register.setOnClickListener {
            performRegister()
        }
        already_have_account_textView.setOnClickListener {
            Log.d(TAG, "try to show login activity")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        select_photo_register.setOnClickListener {
            Log.d(TAG, "Try to show photo selector")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }

    var selectedPhotoUri : Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d(TAG, "Photo was selected")
            selectedPhotoUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            select_photo_imageview_register.setImageBitmap(bitmap)
            select_photo_register.alpha = 0f
        }
    }

    private fun performRegister() {
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d(TAG, "Email is: " + email)
        Log.d(TAG, "Password: $password")

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful)  return@addOnCompleteListener
                    Log.d(TAG, "Successfully created user with uid: ${it.result!!.user.uid}")
                    uploadImageToFirebaseStorage()
                }
                .addOnFailureListener {
                    Log.d(TAG, "Failed to create user: ${it.message}")
                    Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
                    return@addOnFailureListener
                }
    }
    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    Log.d(TAG, "Successfully uploaded image: ${it.metadata?.path}")

                    ref.downloadUrl.addOnSuccessListener {
                        Log.d(TAG, "File Location: $it")

                        saveUserToFirebaseDatabase(it.toString())
                    }
                    val intent = Intent(this, LatestMessagesActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Log.d(TAG, "Failed to upload image to storage: ${it.message}")
                }
    }

//    private fun uploadImageToFirebaseStorage() {
//        if (selectedPhotoUri == null) return
//
//        val filename = UUID.randomUUID().toString()
//        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
//
//        ref.putFile(selectedPhotoUri!!)
//                .addOnSuccessListener {
//                    Log.d(TAG, "Successfully upload image: ${it.metadata?.path}")
//                    ref.downloadUrl.addOnSuccessListener {
//                        Log.d(TAG, "File Location: $it")
//                        saveUserToFirebaseDatabase(it.toString())
//                    }
//                }
//                .addOnFailureListener{
//                        Log.d(TAG, "Failed to upload image to storage: ${it.message}")
//                }
//
//    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, username_edittext_register.text.toString(), profileImageUrl)

        ref.setValue(user)
                .addOnSuccessListener {
                    Log.d(RegisterActivity.TAG, "Finally we saved the user to Firebase Database")
                }
                .addOnFailureListener {
                    Log.d(RegisterActivity.TAG, "Failed to set value to database: ${it.message}")
                }
    }

}

class User(val uid: String, val username: String, val profileImageUrl: String)