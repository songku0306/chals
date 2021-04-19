package com.example.mealgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.solver.widgets.ConstraintAnchor
import com.example.mealgame.databinding.ActivityMainBinding

class MenuActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var tv_gold : TextView
    lateinit var btn_backhome : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        tv_gold = findViewById(R.id.tv_gold)
        btn_backhome = findViewById(R.id.btn_backhome)


        btn_backhome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }

        with(binding) {
            tv_gold.text = intent.getStringExtra("gold")
        }

    }


}