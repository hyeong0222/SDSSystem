package com.B17.sdssystem.manager.task.createtask


import android.app.DatePickerDialog
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.B17.sdssystem.R
import com.B17.sdssystem.data.CreateTask
import com.B17.sdssystem.data.ResponseCreateTask
import com.B17.sdssystem.utils.CalendarFragment
import kotlinx.android.synthetic.main.fragment_create_task_dialog.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.longToast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CreateTaskDialogFragment : DialogFragment(), AnkoLogger {

    lateinit var calendarFragment : CalendarFragment
    val logger = AnkoLogger("Create Task Dialog")
    val REQUEST_CODE = 100


//    interface DialogListener {
//        fun onFinishDialog(inputText: String)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_create_task_dialog, container, false)
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var selectedDate = data?.getStringExtra("formatData")
        error { "Chosen Date === " + selectedDate }

        if (calendarFragment.tag.equals("Start Date")) {
            et_startDate.setText(selectedDate)
        }
        else et_endDate.setText(selectedDate)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        calendarFragment = CalendarFragment()

        dialog.setTitle("Create New Task")
        et_taskName.requestFocus()
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        val fm = activity!!.supportFragmentManager

        btn_startDate.setOnClickListener {
            calendarFragment.setTargetFragment(this@CreateTaskDialogFragment, REQUEST_CODE)
            calendarFragment.show(fm, "Start Date")
        }

        btn_endDate.setOnClickListener {
            calendarFragment.setTargetFragment(this@CreateTaskDialogFragment, REQUEST_CODE)
            calendarFragment.show(fm, "End Date")
        }

        var createTaskModel: CreateTaskViewModel = ViewModelProviders.of(this).get(CreateTaskViewModel::class.java)

        btn_save.setOnClickListener {
            var createTaskList: LiveData<ResponseCreateTask> = createTaskModel.sendCreateTaskRequest(
                "233",
                et_taskName.text.toString(), "1",
                et_description.text.toString(), et_startDate.text.toString(), et_endDate.text.toString()
            )
            createTaskList.observe(this, Observer { s ->
                activity!!.longToast(s!!.msg.get(0))
            })
        }

    }
}

