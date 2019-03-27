package com.B17.sdssystem.manager.task


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.B17.sdssystem.R
import com.B17.sdssystem.adapter.TaskAdapter
import com.B17.sdssystem.data.CreateTask
import com.B17.sdssystem.data.ResponseCreateTask
import com.B17.sdssystem.data.Task
import com.B17.sdssystem.manager.task.createtask.CreateTaskViewModel
import com.B17.sdssystem.manager.task.tasklist.TaskViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TaskFragment : Fragment(), AnkoLogger {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_task, container, false)

        val rv_task = view.findViewById<RecyclerView>(R.id.rv_task)
        val fab_task = view.findViewById<FloatingActionButton>(R.id.fab_task)
        rv_task.layoutManager = LinearLayoutManager(this.context)

        var taskModel : TaskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        var createTaskModel : CreateTaskViewModel = ViewModelProviders.of(this).get(CreateTaskViewModel::class.java)

        var taskList : LiveData<List<Task>> = taskModel.sendTaskRequest()
        taskList.observe(this, Observer { s ->
            rv_task.adapter = TaskAdapter(s)
        })

        fab_task.setOnClickListener { view ->

        }

        var createTaskList : LiveData<List<CreateTask>> = createTaskModel.sendCreateTaskRequest("203", "manager_task", "1",
            "xyz", "2019-03-21", "2019-03-22")
        createTaskList.observe(this, Observer { s ->
            error { s }
        })

        return view
    }
}
