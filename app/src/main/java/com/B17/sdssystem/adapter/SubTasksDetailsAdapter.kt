package com.B17.sdssystem.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.B17.sdssystem.R
import com.B17.sdssystem.data.entries.SubTasksDetailResponse
import kotlinx.android.synthetic.main.item_project.view.*

class SubTasksDetailsAdapter(val subtasksDetails : List<SubTasksDetailResponse>) :RecyclerView.Adapter<SubTasksDetailsAdapter.ViewHolder>() {

    lateinit var onItemClickListener : OnItemClickListener
    public interface OnItemClickListener {
        public fun onItemClick(view : View, position: Int)
    }
















    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_project, p0, false))
    }

    override fun getItemCount(): Int {
        return subtasksDetails?.size?: 0
    }


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.id.text = (subtasksDetails?.get(p1)?.subtaskname)
        p0.status.text = (subtasksDetails?.get(p1)?.subtaskstatus)
        p0.startDate.text = (subtasksDetails?.get(p1)?.startdate)
        p0.desc.text = (subtasksDetails?.get(p1)?.subtaskdesc)
        p0.endDate.text = (subtasksDetails?.get(p1)?.endstart)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(v!!, position)
        }

        init {
            itemView.setOnClickListener(this)
        }
        var id = itemView.tv_id
        var status = itemView.status
        var startDate = itemView.startDate
        var desc = itemView.desc
        var endDate = itemView.endDate
    }

}