package com.B17.sdssystem.manager.project

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.B17.sdssystem.R
import com.B17.sdssystem.data.entries.CreateProjectResponse
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import com.B17.sdssystem.utils.CalendarFragment
import kotlinx.android.synthetic.main.project_dialog_fragment.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectDialog : DialogFragment(), AnkoLogger {

    val logger = AnkoLogger("ProjectDialog")
    val REQUEST_CODE = 100

    lateinit var calendarFragment : CalendarFragment



    interface DialogListener{ fun onFinishDialog(inputText : String)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.project_dialog_fragment, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var selectedDate = data?.getStringExtra("formatData")
        error { "Chosen Date === " + selectedDate }

        if (calendarFragment.tag.equals("Start Date")) {
            tv_start_date.setText(selectedDate)
        }
        else tv_end_date.setText(selectedDate)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        calendarFragment = CalendarFragment()

        dialog.setTitle("Create New Project")
        tv_project_name.requestFocus()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        val fm = activity!!.supportFragmentManager

        btn_start.setOnClickListener {
            calendarFragment.setTargetFragment(this@ProjectDialog, REQUEST_CODE)
            calendarFragment.show(fm, "Start Date")
        }

        btn_end.setOnClickListener {
            calendarFragment.setTargetFragment(this@ProjectDialog, REQUEST_CODE)
            calendarFragment.show(fm, "End Date")
        }

        btn_addProject.setOnClickListener { sendResult() }
    }


    fun sendResult() {

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        val createCall = apiInterface.createProject(tv_project_name.text.toString(), tv_project_status.text.toString(), tv_project_desc.text.toString(), tv_start_date.text.toString(), tv_end_date.text.toString())
        createCall.enqueue(object : Callback<CreateProjectResponse> {
            override fun onFailure(call: Call<CreateProjectResponse>, t: Throwable) {
                logger.info { t.message }
            }
            override fun onResponse(call: Call<CreateProjectResponse>, response: Response<CreateProjectResponse>) {
                val logger = AnkoLogger("ProjectDialog")
                logger.info { response.body() }
                val listener = targetFragment as DialogListener
                listener.onFinishDialog("Project Created.")
            }
        })
        dismiss()
    }
}