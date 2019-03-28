package com.B17.sdssystem.manager.subtask


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.B17.sdssystem.R
import com.B17.sdssystem.data.ResponseCreateSubtask
import kotlinx.android.synthetic.main.subtask_dialog_fragment.*

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.longToast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SubtaskDialogFragment : Fragment(), AnkoLogger {

     var viewModel : SubtaskViewModel  = ViewModelProviders.of(this).get(SubtaskViewModel::class.java!!)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.subtask_dialog_fragment, container, false)
        subtask_create_button.setOnClickListener{
            createSubTask(et_projectid.text.toString(),et_taskid.text.toString(),et_subtaskname.text.toString(),
                           et_subtask_status.text.toString(),et_subtask_desc.text.toString(),et_startdate.text.toString(),
                            et_enddate.text.toString())
        }


        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun createSubTask( project_id : String, task_id : String, sub_task_name : String, sub_task_status : String, sub_task_desc : String,
                               start_date : String, end_date : String){
        var createsubtask : LiveData<ResponseCreateSubtask>? = viewModel.createSubtask(project_id,task_id,sub_task_name,
            sub_task_status,sub_task_desc,start_date,end_date)

        createsubtask?.observe(this, Observer {t ->
            info { t?.msg?.get(0) + " " +  t?.project_id + " "}
            activity?.longToast(" Subtask Created")
        })
    }



}