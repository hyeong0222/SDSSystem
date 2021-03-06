package com.B17.sdssystem.developer.developertask


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.B17.sdssystem.R
import com.B17.sdssystem.data.TaskDetail
import com.B17.sdssystem.developer.viewsubtask.ViewsubtaskFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_task_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.longToast


class TaskDetailFragment : Fragment(), AdapterView.OnItemClickListener, AnkoLogger, View.OnClickListener {

//    val sharedPref: SharedPreferences = activity!!.getSharedPreferences("default", Context.MODE_PRIVATE)

    lateinit var userTaskDetails : TaskDetail
    lateinit var taskDetails : LiveData<TaskDetail>
    var status_id = -1
    lateinit var tasksid : String



    override fun onClick(v: View?) {

        val getValues = activity!!.getSharedPreferences("default", Context.MODE_PRIVATE)

        var viewModel : TaskListViewModel = ViewModelProviders.of(this).get(TaskListViewModel::class.java)
        var response = viewModel.updateTask(userTaskDetails.taskid,userTaskDetails.projectid,
            getValues.getString("userid", "54"), status_id.toString())

        response.observe(this, Observer {
            s -> activity?.longToast(s!!.msg.get(0))
        })
    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        status_id = position
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        var taskInfo = arguments!!.getString("devtasks")
        val gson = Gson()
        val taskInfos = gson.fromJson(taskInfo, TaskDetail::class.java)
        activity?.title = "Tasks Details"


        tv_td_projectId.text = "Project ID: "+ taskInfos.projectid
        tv_td_taskId.text = "Task ID: "+ taskInfos.taskid


        var viewModel : TaskListViewModel = ViewModelProviders.of(this).get(TaskListViewModel::class.java)

        taskDetails  = viewModel.getTaskDetails(taskInfos.taskid,taskInfos.projectid)
        taskDetails.observe(this, Observer { s ->
            info {"testing ------->" + s?.taskname }
            userTaskDetails = s!!
            tv_td_desc.text = "Task Description: " + s?.taskdesc
            tv_td_taskname.text = "Task Name: " +s?.taskname
            tv_td_taskStartDate.text = "Task Start Date: " +s?.startdate
            tv_td_taskStatus.text = "Task Status: " +s?.taskstatus
            tv_td_taskEndDate.text ="Task End Date: " + s?.endstart


            tasksid = userTaskDetails.taskid
        })


        val status = arrayOf("Start", "Incomplete", "Completed")
        spinner.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, status)

        btn_td_submit.setOnClickListener(this)



        btn_subtask.setOnClickListener { v ->
            val arg = Bundle()
            val btn_subtask : Button = v.findViewById(R.id.btn_subtask)


            arg.putString("tasksid", tasksid)
            val viewsubtaskFragment = ViewsubtaskFragment()
            viewsubtaskFragment.arguments = arg

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_developerActivity, viewsubtaskFragment)
                .addToBackStack(null).commit()
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v =  inflater.inflate(R.layout.fragment_task_detail, container, false)


        return v
    }


}
