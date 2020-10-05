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
        button_login_to_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        button_login_submit.setOnClickListener {
            val email = edit_text_login_email.text.toString()
            val password = edit_text_login_password.text.toString()
            if(email == "" || password == ""){
                Toast.makeText(this, "You need to fill in email and password", Toast.LENGTH_SHORT).show()
            } else {
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
                            Toast.makeText(
                                applicationContext,
                                "User or Password Incorrect",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
            }
        }
        button_login_reset_password.setOnClickListener {
            val email = edit_text_login_email.text.toString()
            if (email == "")
                Toast.makeText(this, "You need to enter your registered email first", Toast.LENGTH_SHORT).show()
            else
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Email Sent to Reset Password",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "You Didn't Enter a Registered Email",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
        }
    }
}