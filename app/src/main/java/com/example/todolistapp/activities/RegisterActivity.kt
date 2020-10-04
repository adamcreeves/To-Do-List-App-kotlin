package com.example.todolistapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todolistapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        init()
    }

    private fun init() {
        button_register_to_login.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        button_register_submit.setOnClickListener{
            val email = edit_text_register_email.text.toString()
            val password = edit_text_register_password.text.toString()
            val confirm_password = edit_text_register_confirm_password.text.toString()
            if(!(password != confirm_password && email == "")) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this
                    ) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext, "Registration Successful", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "Email already registered", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            } else {
                Toast.makeText(applicationContext, "Your passwords don't match", Toast.LENGTH_SHORT)
                    .show()
            }
            finish()
        }
    }
}