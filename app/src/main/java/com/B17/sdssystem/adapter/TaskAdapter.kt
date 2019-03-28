package com.B17.sdssystem.adapter

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.B17.sdssystem.R
import com.B17.sdssystem.data.Task
import com.B17.sdssystem.manager.project.ProjectDialog
import com.B17.sdssystem.manager.subtask.SubtaskFragment
import kotlinx.android.synthetic.main.item_task.view.*
import org.jetbrains.anko.AnkoLogger

class TaskAdapter (var taskList : List<Task>?, val context : Context?) : RecyclerView.Adapter <TaskAdapter.MyViewHolder>(), AnkoLogger {

//    val sharedPrefFile : String = "MANAGER"
//    val mPreferences : SharedPreferences = context!!.getSharedPreferences(sharedPrefFile, 0)
//    val preferencesEditor : SharedPreferences.Editor = mPreferences.edit()

    lateinit var onItemClickListener : OnItemClickListener
    interface OnItemClickListener {
        fun onItemClick(view : View, position : Int)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TaskAdapter.MyViewHolder {
        var view = LayoutInflater.from(p0.context).inflate(R.layout.item_task, p0, false)
//        mPreferences = context.getSharedPreferences(sharedPrefFile, 0)
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

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(v!!, position)
        }

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

            itemView.setOnClickListener (this)
        }


    }
}