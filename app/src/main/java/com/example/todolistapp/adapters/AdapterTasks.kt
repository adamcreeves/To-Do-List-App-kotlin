package com.example.todolistapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
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
        var task = mList[position]
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
        fun bind(task: Task, position: Int) {
            var switch = true
            itemView.text_view_row_task_name.setText(task.taskName.toString())
            itemView.text_view_row_task_description.setText(task.taskDescription.toString())
            itemView.button_mark_task_complete.setOnClickListener{
                if(switch){
                    itemView.image_view_completed_task.visibility = VISIBLE
                    itemView.text_view_status.text = "Complete"
                    switch = false
                } else {
                    itemView.image_view_completed_task.visibility = INVISIBLE
                    itemView.text_view_status.text = "Incomplete"
                    switch = true
                }
            }
            itemView.button_delete_task.setOnClickListener{
                var databaseReference = FirebaseDatabase.getInstance().getReference("users")
                databaseReference.child(keyList[position]).setValue(null)
                Toast.makeText(mContext, "Task deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}