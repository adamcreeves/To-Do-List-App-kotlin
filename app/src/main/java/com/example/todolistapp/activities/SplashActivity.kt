package com.example.todolistapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.todolistapp.R

class SplashActivity : AppCompatActivity() {
    private val delayTime: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
        }, delayTime)
        finish()
    }
}