package com.example.todolistapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.R
import com.example.todolistapp.adapters.AdapterTasks
import com.example.todolistapp.models.Task
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    var mList: ArrayList<Task> = ArrayList()
    var keyList: ArrayList<String> = ArrayList()
    var adapterTasks: AdapterTasks? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseReference = FirebaseDatabase.getInstance().getReference(Task.COLLECTION_NAME)
        init()
    }

    private fun init() {
        button_home_add_task.setOnClickListener {
            startActivity(Intent(this, AddNewTaskActivity::class.java))
            getData()
            adapterTasks = AdapterTasks(this, mList, keyList)
            recycler_view_main.layoutManager = LinearLayoutManager(this)
            recycler_view_main.adapter = adapterTasks
        }
    }

    private fun getData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mList = ArrayList()
                for (data in snapshot.children) {
                    var task = data.getValue(Task::class.java)
                    var key = data.key
                    mList.add(task!!)
                    keyList.add(key!!)
                }
                adapterTasks?.setData(mList)
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}