package com.example.todolistapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolistapp.R

class SplashActivity : AppCompatActivity() {
    private val delayTime: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }
}