package com.B17.sdssystem.manager.assignment.employeelist


import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.B17.sdssystem.R
import com.B17.sdssystem.adapter.EmployeeListAdapter
import com.B17.sdssystem.data.EmployeeList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class EmployeeListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_employee_list, container, false)
        val rv_employeeList = view.findViewById<RecyclerView>(R.id.rv_employeeList)

        rv_employeeList.layoutManager = LinearLayoutManager(this.context)

        var model : EmployeeListViewModel = ViewModelProviders.of(this).get(EmployeeListViewModel::class.java)

        var employeeList : LiveData<List<EmployeeList>> = model.sendEmployeeListRequest()
        employeeList.observe(this, Observer { s ->
            rv_employeeList.adapter = EmployeeListAdapter(s)
        })
        return view
    }


}
