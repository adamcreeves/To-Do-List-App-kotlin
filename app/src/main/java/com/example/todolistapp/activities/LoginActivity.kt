package com.example.todolistapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todolistapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        init()
    }

    private fun init() {
        button_login_to_register.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        button_login_submit.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            var email = edit_text_login_email.text.toString()
            var password = edit_text_login_password.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "User or Password Incorrect", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }
}