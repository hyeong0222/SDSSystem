package com.B17.sdssystem.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.B17.sdssystem.R
import com.B17.sdssystem.data.Subtask
import kotlinx.android.synthetic.main.item_subtask.view.*

class SubTaskAdapter(var subtaskList : List<Subtask>, val context : Context?) : RecyclerView.Adapter<SubTaskAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_subtask, p0, false))
    }

    override fun getItemCount(): Int {
        return subtaskList!!.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
       // p0.id.text = (projects?.get(p1)?.id)
        p0.subtaskid.text = ("SUBTASK ID: " + subtaskList?.get(p1)?.subtaskid)
        p0.subtaskname.text = "SUBTASK NAME: " + subtaskList.get(p1).subtaskname
        p0.subtaskdesc.text = "SUBTASK DESC: " + subtaskList.get(p1).subtaskdesc
        p0.subtaskstatus.text ="SUBTASK STATUS: " + subtaskList.get(p1).subtaskstatus
        p0.subtask_enddate.text = "SUBTASK STARTDATE: " +subtaskList.get(p1).endstart
        p0.subtask_startdate.text = "SUBTASK ENDDATE: " +  subtaskList.get(p1).startdate
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var subtaskid = itemView.tv_subtaskid
        var subtaskname = itemView.tv_subtaskname
        var subtaskstatus = itemView.tv_subtaskstatus
        var subtaskdesc = itemView.tv_subtaskdesc
        var subtask_startdate = itemView.tv_st_startdate
        var subtask_enddate = itemView.tv_st_enddate

    }
}