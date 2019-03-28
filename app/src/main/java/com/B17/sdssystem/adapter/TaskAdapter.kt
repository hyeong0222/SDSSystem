package com.B17.sdssystem.adapter

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.B17.sdssystem.R
import com.B17.sdssystem.data.Task
import kotlinx.android.synthetic.main.item_task.view.*
import org.jetbrains.anko.AnkoLogger

class TaskAdapter (var taskList : List<Task>?) : RecyclerView.Adapter <TaskAdapter.MyViewHolder>(), AnkoLogger {

//    lateinit var mPreferences : SharedPreferences
//    val sharedPrefFile : String = "com.B17.sdssystem";

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TaskAdapter.MyViewHolder {
        var view = LayoutInflater.from(p0.context).inflate(R.layout.item_task, p0, false)
//        mPreferences = SharedPreferences(sharedPrefFile, MODE_PRIVATE)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: TaskAdapter.MyViewHolder, p1: Int) {
//            = p0.bind(taskList!!.get(p1), p1, listener)
        var task: Task = taskList!!.get(p1)

        p0.id.setText(task.taskid)
        p0.name.setText(task.taskname)
        p0.status.setText(task.taskstatus)
        p0.description.setText(task.taskdesc)
        p0.startDate.setText(task.startdate)
        p0.endDate.setText(task.endstart)
    }


    override fun getItemCount(): Int {
        return taskList!!.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

//        fun bind(item : Task, pos : Int, listener: (Int) -> Unit) = with(itemView) {
//            tv_taskId.setText(item.taskid)
//            tv_taskName.setText(item.taskname)
//            tv_taskStatus.setText(item.taskstatus)
//            tv_taskDescription.setText(item.taskdesc)
//            tv_taskStartDate.setText(item.startdate)
//            tv_taskEndDate.setText(item.endstart)
//
//            itemView.setOnClickListener {  }
//
////            var id = findViewById(R.id.tv_taskId)
////            var name = findViewById(R.id.tv_taskName)
////            var status = findViewById(R.id.tv_taskStatus)
////            var description = findViewById(R.id.tv_taskDescription)
////            var startDate = findViewById(R.id.tv_taskStartDate)
////            var endDate = findViewById(R.id.tv_taskEndDate)
//        }

        var id : TextView
        var name : TextView
        var status : TextView
        var description : TextView
        var startDate : TextView
        var endDate : TextView

        init{
            id = itemView.tv_taskId
            name = itemView.tv_taskName
            status = itemView.tv_taskStatus
            description = itemView.tv_taskDescription
            startDate = itemView.tv_taskStartDate
            endDate = itemView.tv_taskEndDate

            itemView.setOnClickListener {v: View ->
                var position : Int = adapterPosition


            }
        }


    }
}