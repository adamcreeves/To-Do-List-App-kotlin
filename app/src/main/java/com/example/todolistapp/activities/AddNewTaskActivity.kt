package com.example.todolistapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todolistapp.R
import com.example.todolistapp.models.Task
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_new_task.*

class AddNewTaskActivity : AppCompatActivity() {
    private lateinit var firebaseDatabase: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task)
        firebaseDatabase = FirebaseDatabase.getInstance()
        init()
    }

    private fun init() {
        button_add_task_submit.setOnClickListener {
            var taskName = edit_text_task_name.text.toString()
            var taskDescription = edit_text_task_description.text.toString()
            if (taskName != "") {
                var task = Task(taskName, taskDescription)
                var databaseReference = firebaseDatabase.getReference("tasks")
                var taskId = databaseReference.push().key
                databaseReference.child(taskId!!).setValue(task)
                Toast.makeText(
                    applicationContext,
                    "Your new task has been added",
                    Toast.LENGTH_SHORT
                )
                    .show()
                finish()
            } else Toast.makeText(
                applicationContext,
                "You have to fill in task name",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}