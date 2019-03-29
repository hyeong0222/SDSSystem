package com.B17.sdssystem.developer.developertask


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.B17.sdssystem.R
import com.B17.sdssystem.adapter.DevTaskAdapter
import com.B17.sdssystem.data.TaskList
import com.B17.sdssystem.data.TaskListResponse
import com.google.gson.Gson
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class TaskListFragment : Fragment(), DevTaskAdapter.OnItemClickListener, AnkoLogger{

    lateinit var adapter : DevTaskAdapter
    override fun onItemClick(view: View, position: Int) {

        val gson = Gson()
        val json = gson.toJson(adapter.devTaskList.get(position))
        info { adapter.devTaskList.get(position) }

        val editor = activity!!.getSharedPreferences("default", Context.MODE_PRIVATE).edit()
        editor.putString("devtasks", json).apply()

        val args = Bundle()
        args.putString("devtasks", json)
        val taskDetailFragment = TaskDetailFragment()

        taskDetailFragment.arguments = args

        activity!!.supportFragmentManager.beginTransaction().replace(R.id.dev_act_container, taskDetailFragment)
            .addToBackStack(null).commit()
    }


    lateinit var viewModel : TaskListViewModel
    lateinit var devTaskList : LiveData<TaskListResponse>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var v = inflater.inflate(R.layout.fragment_task_list, container, false)

        var rv : RecyclerView = v.findViewById(R.id.tasklist_recyclerView)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(activity)

         viewModel = ViewModelProviders.of(this).get(TaskListViewModel::class.java)

        devTaskList = viewModel.requestTaskList("53")
        devTaskList.observe(this, Observer { s ->
            adapter = DevTaskAdapter(s!!.taskList, context)
            rv.adapter = adapter
            adapter.onItemClickListener = this
        })

        return v
    }


}
