package com.example.todolistapp.activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.R
import com.example.todolistapp.adapters.AdapterTasks
import com.example.todolistapp.models.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    lateinit var auth: FirebaseAuth
    var mList: ArrayList<Task> = ArrayList()
    var keyList: ArrayList<String> = ArrayList()
    var adapterTasks: AdapterTasks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference(Task.COLLECTION_NAME)
        init()
    }

    private fun init() {
        getData()
        adapterTasks = AdapterTasks(this, mList, keyList)
        recycler_view_main.layoutManager = LinearLayoutManager(this)
        recycler_view_main.adapter = adapterTasks
        button_home_add_task.setOnClickListener {
            startActivity(Intent(this, AddNewTaskActivity::class.java))
        }
        button_home_logout.setOnClickListener{
            dialogLogout()
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
                Toast.makeText(
                    applicationContext,
                    "An Error occurred retrieving the data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun dialogLogout() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Log Out")
        builder.setMessage("Are you sure you want to log out?")
        builder.setNegativeButton("No", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                dialog?.dismiss()
            }
        })
        builder.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                auth.signOut()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }
        })
        var myAlertDialog = builder.create()
        myAlertDialog.show()
    }

}