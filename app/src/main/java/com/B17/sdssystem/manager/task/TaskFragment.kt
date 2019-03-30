package com.B17.sdssystem.manager.task


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
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
import kotlinx.android.synthetic.main.fragment_task.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TaskFragment : Fragment(), AnkoLogger, TaskAdapter.OnItemClickListener, DialogInterface.OnDismissListener {

    private var tasks : ArrayList<Task> = ArrayList<Task>()
    private lateinit var taskAdapter : TaskAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_task, container, false)

        val rv_task = view.findViewById<RecyclerView>(R.id.rv_task)
        val fab_task = view.findViewById<FloatingActionButton>(R.id.fab_task)
        rv_task.layoutManager = LinearLayoutManager(this.context)

        tasks.clear()
        networkCall()
        taskAdapter = TaskAdapter(tasks, context)
        rv_task.adapter = taskAdapter
        taskAdapter.onItemClickListener = this

        fab_task.setOnClickListener { view ->
            showEditDialog()
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        error { "onResume" }
        tasks.clear()

        val d = CreateTaskDialogFragment()

        d.setOnDismissListener(DialogInterface.OnDismissListener {
            error { "-------------------------- Dialog Dimiss Check in onResume" }

            networkCall()
            rv_task.removeAllViews()
            taskAdapter = TaskAdapter(tasks, context)
            taskAdapter.notifyDataSetChanged()
            rv_task.adapter = taskAdapter

            taskAdapter.onItemClickListener = this
        })

        d.show(fragmentManager, "Dialog")
    }

    override fun onItemClick(view: View, position: Int) {

        val editor = activity!!.getSharedPreferences("MANAGER", Context.MODE_PRIVATE).edit()
        editor.putString("task", taskAdapter.taskList?.get(position)?.taskid).apply()

        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_managerActivity, SubtaskFragment())
            .addToBackStack(null).commit()
    }

    private fun networkCall(){

        val mPreferences = activity!!.getSharedPreferences("MANAGER", Context.MODE_PRIVATE)
        var projectID : String? = mPreferences.getString("project", null)

        var taskModel : TaskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        var taskList : LiveData<List<Task>> = taskModel.sendTaskRequest()
        taskList.observe(this, Observer { s ->
            tasks.clear()
            for (i in 0..s!!.size - 1) {
                if (s.get(i).projectid.equals(projectID)) {
                    tasks.add(s.get(i))
                }
            }
        })
    }

    private fun showEditDialog() {

        val fm = activity!!.supportFragmentManager
        val fg = CreateTaskDialogFragment()

        fg.setTargetFragment(this, 300)
        fg.show(fm, "New Task")
    }

    override fun onDismiss(dialog: DialogInterface?) {
        error { "-------------------------- Dialog Dismiss Check" }
    }
}
