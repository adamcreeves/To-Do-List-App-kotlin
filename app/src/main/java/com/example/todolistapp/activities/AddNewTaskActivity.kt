package com.example.todolistapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolistapp.R
import kotlinx.android.synthetic.main.activity_add_new_task.*

class AddNewTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task)
        init()
    }

    private fun init() {
        button_add_task_submit.setOnClickListener{
            finish()
        }
    }
}