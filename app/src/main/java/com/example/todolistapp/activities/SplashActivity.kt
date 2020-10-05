package com.example.todolistapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.todolistapp.R
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    private val delayTime: Long = 2500
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        auth = FirebaseAuth.getInstance()
        init()
    }

    private fun init() {
        val handler = Handler()
        val user = auth.currentUser
        handler.postDelayed({
            if(user != null) startActivity(Intent(this, MainActivity::class.java))
            else startActivity(Intent(this, LoginActivity::class.java))
        }, delayTime)
    }
}