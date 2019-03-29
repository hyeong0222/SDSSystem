package com.B17.sdssystem.manager.assignment.employeelist


import android.arch.lifecycle.*
import android.content.Context
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
import com.B17.sdssystem.manager.assignment.assign.AssignFragment
import org.jetbrains.anko.AnkoLogger

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class EmployeeListFragment : Fragment(), AnkoLogger, EmployeeListAdapter.OnItemClickListener {

    private lateinit var employeeListAdapter : EmployeeListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_employee_list, container, false)
        val rv_employeeList = view.findViewById<RecyclerView>(R.id.rv_employeeList)

        rv_employeeList.layoutManager = LinearLayoutManager(this.context)

        var model : EmployeeListViewModel = ViewModelProviders.of(this).get(EmployeeListViewModel::class.java)

        var employeeList : LiveData<List<EmployeeList>> = model.sendEmployeeListRequest()
        employeeList.observe(this, Observer { s ->
            employeeListAdapter = EmployeeListAdapter(s, context)
            rv_employeeList.adapter = employeeListAdapter
            employeeListAdapter.onItemClickListener = this
        })
        return view
    }

    override fun onItemClick(view: View, position: Int) {
        val editor = activity!!.getSharedPreferences("MANAGER", Context.MODE_PRIVATE).edit()
        editor.putString("employee", employeeListAdapter.employeeList?.get(position)?.empid).apply()

        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_managerActivity, AssignFragment())
            .addToBackStack(null).commit()
    }

}
