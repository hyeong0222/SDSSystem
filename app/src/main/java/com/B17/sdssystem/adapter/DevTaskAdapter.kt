package com.B17.sdssystem.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.B17.sdssystem.R
import com.B17.sdssystem.data.TaskList
import kotlinx.android.synthetic.main.item_dev_task.view.*

class DevTaskAdapter(var devTaskList : List<TaskList>?, var context : Context?) : RecyclerView.Adapter<DevTaskAdapter.ViewHolder>(){

  lateinit var onItemClickListener : OnItemClickListener

    public interface OnItemClickListener {
        public fun onItemClick(view : View, position: Int)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return MyViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_dev_task,p0,false))
        var view = LayoutInflater.from(p0.context).inflate(R.layout.item_dev_task, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      //  return devTaskList?.size?: 0
        if (devTaskList ==null){
            return 0
        }
        return devTaskList!!.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
         p0.tv_projectid.text = devTaskList!!.get(p1).projectid
         p0.tv_taskid.text = "TASK ID:     "+devTaskList!!.get(p1).taskid
         p0.tv_subtaskid.text = devTaskList!!.get(p1).subtaskid
    }


   inner class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview),  View.OnClickListener{
        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(v!!, position)
        }
        init {
            itemView.setOnClickListener(this)
        }

        var tv_projectid = itemview.tv_project_id
        var tv_taskid = itemview.tv_taskid
        var tv_subtaskid = itemview.tv_subtask_id
    }

}