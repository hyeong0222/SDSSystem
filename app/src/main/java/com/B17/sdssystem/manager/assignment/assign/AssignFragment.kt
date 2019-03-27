package com.B17.sdssystem.manager.assignment.assign


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.B17.sdssystem.R
import com.B17.sdssystem.data.Task
import com.B17.sdssystem.data.entries.Project

import com.B17.sdssystem.manager.project.ProjectContract
import com.B17.sdssystem.manager.project.ProjectPresenter
import com.B17.sdssystem.manager.task.tasklist.TaskViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AssignFragment : Fragment(), ProjectContract.View {

    var projectsList : List<String> = ArrayList<String>()
    var tasksList : List<String> = ArrayList<String>()
    var subtasksList : List<String> = ArrayList<String>()

    lateinit var projectListSpinner : Spinner
    lateinit var taskListSpinner : Spinner
    lateinit var subtaskListSpinner : Spinner



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_assign, container, false)
        projectListSpinner = view.findViewById(R.id.spinner_project)
        taskListSpinner = view.findViewById(R.id.spinner_task)
        projectListSpinner.onItemSelectedListener
        presenter.getProjects()
        taskSpinner()

        return view
    }

    override fun setAdapter(projects: List<Project>?) {

        for (i in 0..projects!!.size-1) {
            var item : String = projects.get(i).id + ". " + projects.get(i).projectname
            projectsList += item
        }

        val aa = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, projectsList)
        projectListSpinner.adapter = aa
    }

    val presenter : ProjectPresenter by lazy {
        ProjectPresenter(
            this
        )
    }

    fun taskSpinner() {
        var taskModel : TaskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        var taskList : LiveData<List<Task>> = taskModel.sendTaskRequest()
        taskList.observe(this, Observer { s ->
            for (i in 0..s!!.size-1) {
                var item : String = s.get(i).taskid + ". " + s.get(i).taskname
                tasksList += item
            }

            val aa = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, tasksList)
            taskListSpinner.adapter = aa

        })
    }


}
