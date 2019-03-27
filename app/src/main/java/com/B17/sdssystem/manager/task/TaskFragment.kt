package com.B17.sdssystem.manager.task


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
import com.B17.sdssystem.data.ResponseTask
import com.B17.sdssystem.manager.task.createtask.CreateTaskContract
import com.B17.sdssystem.manager.task.createtask.CreateTaskPresenter
import com.B17.sdssystem.manager.task.tasklist.TaskContract
import com.B17.sdssystem.manager.task.tasklist.TaskPresenter
import kotlinx.android.synthetic.main.fragment_task.*
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
class TaskFragment : Fragment(), TaskContract.View, CreateTaskContract.View, AnkoLogger {

    lateinit var taskPresenter : TaskContract.Presenter
    lateinit var createTaskPresenter : CreateTaskContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_task, container, false)

        val rv_task = view.findViewById<RecyclerView>(R.id.rv_task)
        val fab_task = view.findViewById<FloatingActionButton>(R.id.fab_task)
        rv_task.layoutManager = LinearLayoutManager(this.context)

        taskPresenter = TaskPresenter(this)
        taskPresenter.sendTaskRequest()

        fab_task.setOnClickListener { view ->

        }

        createTaskPresenter = CreateTaskPresenter(this)
        createTaskPresenter.sendCreateTaskRequest("203", "manager_task", "1",
            "xyz", "2019-03-21", "2019-03-22")

        return view
    }

    override fun getTaskResponse(response: Response<ResponseTask>?) {
        val responseTask : ResponseTask = response?.body()!!
        rv_task.adapter = TaskAdapter(responseTask.taskList)
    }

    override fun getCreateTaskResponse(response: Response<ResponseCreateTask>?) {
        val responseCreateTask : ResponseCreateTask = response?.body()!!
        error { responseCreateTask.createTaskList }
    }
}
