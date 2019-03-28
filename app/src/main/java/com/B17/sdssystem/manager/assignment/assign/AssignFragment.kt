package com.B17.sdssystem.manager.assignment.assign


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.B17.sdssystem.R
import com.B17.sdssystem.data.Subtask
import com.B17.sdssystem.data.SubtaskResponse
import com.B17.sdssystem.data.Task
import com.B17.sdssystem.data.entries.Project

import com.B17.sdssystem.manager.project.ProjectContract
import com.B17.sdssystem.manager.project.ProjectPresenter
import com.B17.sdssystem.manager.subtask.SubtaskViewModel
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

    var projects : List<Project> = ArrayList<Project>()
    var tasks : List<Task>? = ArrayList<Task>()
    var subtasks : List<Subtask>? = ArrayList<Subtask>()

    lateinit var projectListSpinner : Spinner
    lateinit var taskListSpinner : Spinner
    lateinit var subtaskListSpinner : Spinner



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_assign, container, false)
        projectListSpinner = view.findViewById(R.id.spinner_project)
        taskListSpinner = view.findViewById(R.id.spinner_task)
        subtaskListSpinner = view.findViewById(R.id.spinner_subtask)

        presenter.getProjects()
        projectListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                taskSpinner(projects.get(position).id)
                taskListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        subtaskSpinner(tasks!!.get(position).taskid)
                    }
                }
            }
        }
        return view
    }

    override fun setAdapter(projects: List<Project>?) {

        for (i in 0..projects!!.size-1) {
            this.projects = projects
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

    fun taskSpinner(id : String) {
        var taskModel : TaskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        var taskList : LiveData<List<Task>> = taskModel.sendTaskRequest()
        taskList.observe(this, Observer { s ->
            tasks = s
            for (i in 0..s!!.size-1) {
                if (s.get(i).projectid == id) {
                    var item : String = s.get(i).taskid + ". " + s.get(i).taskname
                    tasksList += item
                }
            }

            val aa = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, tasksList)
            taskListSpinner.adapter = aa
        })
    }

    fun subtaskSpinner(taskid : String) {
        var subtaskModel : SubtaskViewModel = ViewModelProviders.of(this).get(SubtaskViewModel::class.java)
        var subtaskList : LiveData<SubtaskResponse>? = subtaskModel.getSubtaskList()
        subtaskList?.observe(this, Observer { s ->
            subtasks = s?.subtaskList
            var subtasks : List<Subtask>? = s?.subtaskList
            for (i in 0..subtasks!!.size - 1) {
                if (subtasks.get(i).taskid == taskid){
                    var item : String = subtasks.get(i).subtaskid + ". " + subtasks.get(i).subtaskname
                    subtasksList += item
                }
            }
            val aa = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, subtasksList)
            subtaskListSpinner.adapter = aa
        })
    }


}
