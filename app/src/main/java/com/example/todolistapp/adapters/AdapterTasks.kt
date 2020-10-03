package com.example.todolistapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.models.Task
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.row_adapter_task.view.*

class AdapterTasks(
    private var mContext: Context,
    private var mList: ArrayList<Task>,
    private var keyList: ArrayList<String>
) :
    RecyclerView.Adapter<AdapterTasks.mViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_adapter_task, parent, false)
        return mViewHolder(view)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        var user = mList[position]
        holder.bind(user, position)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(list: ArrayList<Task>) {
        notifyDataSetChanged()
        mList = list
    }

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task, position: Int) {
            itemView.text_view_row_task_name.text = task.taskName
            itemView.text_view_row_task_status.text = task.status
            itemView.button_mark_task_complete.setOnClickListener{
                var databaseReference = FirebaseDatabase.getInstance().getReference("tasks")
                databaseReference.child(keyList[position]).setValue(Task(task.taskName, task.taskDescription, "Complete"))
                Toast.makeText(mContext, "User updated successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
}