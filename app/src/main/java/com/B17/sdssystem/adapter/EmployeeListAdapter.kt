package com.B17.sdssystem.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.B17.sdssystem.R
import com.B17.sdssystem.data.EmployeeList
import kotlinx.android.synthetic.main.item_employee_list.view.*

class EmployeeListAdapter (val employeeList : List<EmployeeList>?, val context : Context?)
    : RecyclerView.Adapter<EmployeeListAdapter.MyViewHolder>() {

    lateinit var onItemClickListener : OnItemClickListener
    interface OnItemClickListener {
        fun onItemClick(view : View, position : Int)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): EmployeeListAdapter.MyViewHolder {
        var view : View = LayoutInflater.from(p0.context).inflate(R.layout.item_employee_list, p0, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: EmployeeListAdapter.MyViewHolder, p1: Int) {
        var employeeList : EmployeeList = employeeList!!.get(p1)

        p0.empid.setText(employeeList.empid)
        p0.empfirstname.setText(employeeList.empfirstname)
        p0.emplastname.setText(employeeList.emplastname)
        p0.empmobile.setText(employeeList.empmobile)
        p0.empemail.setText(employeeList.empemail)
        p0.empdesignation.setText(employeeList.empdesignation)
        p0.dateofjoining.setText(employeeList.dateofjoining)
    }

    override fun getItemCount(): Int {
        return employeeList!!.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(v!!, position)
        }

        var empid : TextView
        var empfirstname : TextView
        var emplastname : TextView
        var empemail : TextView
        var empmobile : TextView
        var empdesignation : TextView
        var dateofjoining : TextView

        init {
            empid = itemView.tv_employeeID
            empfirstname = itemView.tv_employeeFName
            emplastname = itemView.tv_employeeLName
            empemail = itemView.tv_employeeEmail
            empmobile = itemView.tv_employeeMobile
            empdesignation = itemView.tv_employeeDesignation
            dateofjoining = itemView.tv_employeeDateOfJoining

            itemView.setOnClickListener (this)

        }
    }
}