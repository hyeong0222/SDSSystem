package com.B17.sdssystem.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.B17.sdssystem.R
import com.B17.sdssystem.data.EmployeeList
import kotlinx.android.synthetic.main.item_employee_list.view.*

class EmployeeListAdapter (val employeeList : List<EmployeeList>?) : RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): EmployeeListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_employee_list, p0, false))
    }

    override fun onBindViewHolder(p0: EmployeeListAdapter.ViewHolder, p1: Int) {
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

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var empid = itemView.tv_employeeID
        var empfirstname = itemView.tv_employeeFName
        var emplastname = itemView.tv_employeeLName
        var empemail = itemView.tv_employeeEmail
        var empmobile = itemView.tv_employeeMobile
        var empdesignation = itemView.tv_employeeDesignation
        var dateofjoining = itemView.tv_employeeDateOfJoining
    }
}