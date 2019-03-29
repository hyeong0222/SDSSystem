package com.B17.sdssystem.developer.viewsubtask


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.B17.sdssystem.R
import com.B17.sdssystem.data.entries.SubTasksDetailResponse


import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sub_tasks_detail.*
import kotlinx.android.synthetic.main.fragment_task_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */














class SubTasksDetailFragment : Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener {
    lateinit var subTasks : SubTasksDetailResponse
    override fun onClick(v: View?) {
        val viewModel = ViewModelProviders.of(this).get(SubTasksDetailViewModel::class.java)

        var response = viewModel.updateSubTasks(subTasks.subtaskid, subTasks.taskid, subTasks.projectid, "", status_id.toString())





        response.observe(this, Observer { s -> Toast.makeText(context, s?.msg?.get(0), Toast.LENGTH_LONG).show() })
    }

    var status_id = -1
    override fun onNothingSelected(parent: AdapterView<*>?) {






    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        status_id = position
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment













        return inflater.inflate(R.layout.fragment_sub_tasks_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val details = arguments!!.getString("subtasks")
        val gson = Gson()
        subTasks = gson.fromJson(details, SubTasksDetailResponse::class.java)
        activity?.title = "SubTask's Details"



        tv_details.text = subTasks.subtaskname
        start_date.text = subTasks.startdate


        end_date.text = subTasks.endstart

        des.text = subTasks.subtaskdesc










        val status = arrayOf("Choose Status", "Start", "imcomplete", "complete")
        spinner_task_details.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, status)




        button3.setOnClickListener(this)

    }

}






