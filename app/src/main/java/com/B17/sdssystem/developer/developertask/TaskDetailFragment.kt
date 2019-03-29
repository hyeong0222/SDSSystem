package com.B17.sdssystem.developer.developertask


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.B17.sdssystem.R
import com.B17.sdssystem.data.TaskDetail
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_task_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class TaskDetailFragment : Fragment(), AdapterView.OnItemClickListener, AnkoLogger {

   lateinit var taskDetails : LiveData<TaskDetail>
    var status_id = -1


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        status_id = position
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var taskInfo = arguments!!.getString("devtasks")
        val gson = Gson()
        val taskInfos = gson.fromJson(taskInfo, TaskDetail::class.java)


        tv_td_projectId.text = "PROJECT ID: "+ taskInfos.projectid
        tv_td_taskId.text = "TASK ID: "+ taskInfos.taskid


        var viewModel : TaskListViewModel = ViewModelProviders.of(this).get(TaskListViewModel::class.java)

          taskDetails  = viewModel.getTaskDetails(taskInfos.taskid,taskInfos.projectid)

        taskDetails.observe(this, Observer { s ->
            info {"testing ------->" + s?.taskname }

            tv_td_desc.text = "TASK DESCRIPTION: " + s?.taskdesc
            tv_td_taskname.text = "Task NAME: " +s?.taskname
            tv_td_taskStartDate.text = "TASK START DATE: " +s?.startdate
            tv_td_taskStatus.text = "TASK STATUS " +s?.taskstatus
            tv_td_taskEndDate.text ="TASK END DATE " + s?.endstart

        })


        val status = arrayOf("Start", "imcomplete", "complete")
        spinner.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, status)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v =  inflater.inflate(R.layout.fragment_task_detail, container, false)

          return v
    }


}
