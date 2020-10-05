package com.example.todolistapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.activities.LoginActivity
import com.example.todolistapp.models.Task
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.row_adapter_task.view.*

class AdapterTasks(
    private var mContext: Context,
    private var mList: ArrayList<Task>,
    private var keyList: ArrayList<String>
) :
    RecyclerView.Adapter<AdapterTasks.mViewHolder>() {
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_adapter_task, parent, false)
        return mViewHolder(view)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val task = mList[position]
        holder.bind(task, position)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(list: ArrayList<Task>) {
        notifyDataSetChanged()
        mList = list
    }

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(task: Task, position: Int) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("tasks")
            var status = task.status
            if (status == "complete") {
                itemView.image_view_completed_task.visibility = VISIBLE
            }
            itemView.text_view_row_task_name.text = task.taskName.toString()
            itemView.text_view_row_task_description.text = task.taskDescription.toString()
            itemView.text_view_status.text = task.status
            itemView.button_mark_task_complete.setOnClickListener {
                if (status == "incomplete") {
                    itemView.image_view_completed_task.visibility = VISIBLE
                    itemView.text_view_status.text = "complete"
                    databaseReference.child(keyList[position]).setValue(Task(task.taskName, task.taskDescription, "complete"))
                } else {
                    itemView.image_view_completed_task.visibility = INVISIBLE
                    itemView.text_view_status.text = "incomplete"
                    databaseReference.child(keyList[position]).setValue(Task(task.taskName, task.taskDescription, "incomplete"))
                }
            }
            itemView.button_delete_task.setOnClickListener {
                var builder = AlertDialog.Builder(mContext)
                builder.setTitle("Confirm Delete")
                builder.setMessage("Are you sure you want to delete this task?")
                builder.setNegativeButton(
                    "No"
                ) { dialog, p1 -> dialog?.dismiss() }
                builder.setPositiveButton(
                    "Yes"
                ) { p0, p1 ->
                    databaseReference.child(keyList[position]).setValue(null)
                    Toast.makeText(mContext, "Task deleted", Toast.LENGTH_SHORT).show()
                }
                var myAlertDialog = builder.create()
                myAlertDialog.show()


            }
        }
    }
}