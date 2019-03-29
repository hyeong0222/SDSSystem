package com.B17.sdssystem.manager.task.createtask


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
import kotlinx.android.synthetic.main.fragment_create_task_dialog.*
import org.jetbrains.anko.longToast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CreateTaskDialogFragment : DialogFragment() {

    interface DialogListener {
        fun onFinishDialog(inputText: String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_create_task_dialog, container, false)

        // Inflate the layout for this fragment

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog.setTitle("Create New Task")
        et_taskName.requestFocus()
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        var createTaskModel: CreateTaskViewModel = ViewModelProviders.of(this).get(CreateTaskViewModel::class.java)

        btn_save.setOnClickListener {
            var createTaskList: LiveData<ResponseCreateTask> = createTaskModel.sendCreateTaskRequest(
                "203",
                et_taskName.text.toString(), "1",
                et_description.text.toString(), et_startDate.text.toString(), et_endDate.text.toString()
            )
            createTaskList.observe(this, Observer { s ->
                activity!!.longToast(s!!.msg.get(0))
            })
        }

    }
}

