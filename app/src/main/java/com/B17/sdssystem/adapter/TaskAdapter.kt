package com.B17.sdssystem.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.B17.sdssystem.R
import com.B17.sdssystem.data.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter (var taskList : List<Task>) : RecyclerView.Adapter <TaskAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TaskAdapter.MyViewHolder {
        var view = LayoutInflater.from(p0.context).inflate(R.layout.item_task, p0, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: TaskAdapter.MyViewHolder, p1: Int) {
        var task : Task = taskList.get(p1)

        p0.id.setText(task.taskid)
        p0.name.setText(task.taskname)
        p0.status.setText(task.taskstatus)
        p0.description.setText(task.taskdesc)
        p0.startDate.setText(task.startdate)
        p0.endDate.setText(task.endstart)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var id = itemView.tv_taskId
        var name = itemView.tv_taskName
        var status = itemView.tv_taskStatus
        var description = itemView.tv_taskDescription
        var startDate = itemView.tv_taskStartDate
        var endDate = itemView.tv_taskEndDate
    }
}