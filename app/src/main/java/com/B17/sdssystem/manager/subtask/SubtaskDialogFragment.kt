package com.B17.sdssystem.manager.subtask


import android.app.Dialog
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import com.B17.sdssystem.R
import com.B17.sdssystem.data.ResponseCreateSubtask
import com.B17.sdssystem.utils.CalendarFragment
import kotlinx.android.synthetic.main.subtask_dialog_fragment.*

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.error
import org.jetbrains.anko.longToast





class SubtaskDialogFragment : DialogFragment(), AnkoLogger {

    lateinit var subtask_create_button : Button

    val REQUEST_CODE = 100

    val mPreferences = activity!!.getSharedPreferences("MANAGER", Context.MODE_PRIVATE)
    var taskID : String? = mPreferences.getString("task", null)
    var projectID : String? = mPreferences.getString("project", null)

   lateinit var ondisListener: DialogInterface.OnDismissListener
    lateinit var calendarFragment : CalendarFragment
    interface DialogListener{ fun onFinishDialog(inputText : String)}

    var logger = AnkoLogger("Subtask Dialog Fragment")

  lateinit var viewModel  : SubtaskViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(com.B17.sdssystem.R.layout.subtask_dialog_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(SubtaskViewModel::class.java)

        subtask_create_button = v.findViewById(com.B17.sdssystem.R.id.subtask_create_button)

        subtask_create_button.setOnClickListener{
            createSubTask(projectID.toString(),taskID.toString(),et_subtaskname.text.toString(),
                et_subtask_status.text.toString(),et_subtask_desc.text.toString(),et_startdate.text.toString(),
                et_enddate.text.toString())
        }

        return v
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var selectedDate = data?.getStringExtra("formatData")
      //  error { "Chosen Date === " + selectedDate }

        if (calendarFragment.tag.equals("Start Date")) {
            et_startdate.setText(selectedDate)
        }
        else et_enddate.setText(selectedDate)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarFragment = CalendarFragment()

        dialog.setTitle("Create New Task")
       // tv_project_name.requestFocus()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        val fm = activity!!.supportFragmentManager

        btn_startdate.setOnClickListener {
            calendarFragment.setTargetFragment(this@SubtaskDialogFragment, REQUEST_CODE)
            calendarFragment.show(fm, "Start Date")
        }

        btn_endDate.setOnClickListener {
            calendarFragment.setTargetFragment(this@SubtaskDialogFragment, REQUEST_CODE)
            calendarFragment.show(fm, "End Date")
        }


        /*btn_addProject.setOnClickListener { sendResult() }*/
    }

    private fun createSubTask( project_id : String, task_id : String, sub_task_name : String, sub_task_status : String, sub_task_desc : String,
                               start_date : String, end_date : String){
        var createsubtask : LiveData<ResponseCreateSubtask>? = viewModel.createSubtask(project_id,task_id,sub_task_name,
            sub_task_status,sub_task_desc,start_date,end_date)

        createsubtask?.observe(this, Observer {t ->
            info { t?.msg?.get(0) + " " +  t?.project_id }
            activity?.longToast(" Subtask Created")
        })
    dismiss()
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        if(ondisListener != null){

         ondisListener.onDismiss(dialog)

        }
    }



    fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener) {
        this.ondisListener = onDismissListener
    }
}
