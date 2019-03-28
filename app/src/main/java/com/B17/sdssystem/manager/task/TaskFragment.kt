package com.B17.sdssystem.manager.task


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
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
import com.B17.sdssystem.data.Task
import com.B17.sdssystem.manager.subtask.SubtaskFragment
import com.B17.sdssystem.manager.task.createtask.CreateTaskDialogFragment
import com.B17.sdssystem.manager.task.tasklist.TaskViewModel
import com.google.gson.Gson
import org.jetbrains.anko.AnkoLogger

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TaskFragment : Fragment(), AnkoLogger, TaskAdapter.OnItemClickListener {

    private lateinit var taskAdapter : TaskAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_task, container, false)

        val rv_task = view.findViewById<RecyclerView>(R.id.rv_task)
        val fab_task = view.findViewById<FloatingActionButton>(R.id.fab_task)
        rv_task.layoutManager = LinearLayoutManager(this.context)

        var taskModel : TaskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)

        var taskList : LiveData<List<Task>> = taskModel.sendTaskRequest()
        taskList.observe(this, Observer { s ->
            taskAdapter = TaskAdapter(s, context)
            rv_task.adapter = taskAdapter
            taskAdapter.onItemClickListener = this
        })

        fab_task.setOnClickListener { view ->
            showEditDialog()
        }

        return view
    }

    override fun onItemClick(view: View, position: Int) {
        val gson = Gson()
        val json = gson.toJson(taskAdapter.taskList?.get(position))

        val editor = activity!!.getSharedPreferences("MANAGER", Context.MODE_PRIVATE).edit()
        editor.putString("tasks", json).apply()

        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_managerActivity, SubtaskFragment())
            .addToBackStack(null).commit()
    }

    private fun showEditDialog() {

        val fm = activity!!.supportFragmentManager
        val fg = CreateTaskDialogFragment()

        fg.setTargetFragment(this, 300)
        fg.show(fm, "New Task")
    }
}
