package com.example.todolistapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolistapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        button_login_to_register.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        button_login_submit.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}