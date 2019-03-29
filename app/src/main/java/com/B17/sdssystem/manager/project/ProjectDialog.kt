package com.B17.sdssystem.manager.project

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.B17.sdssystem.R
import com.B17.sdssystem.data.entries.AssignResponse
import com.B17.sdssystem.data.entries.CreateProjectResponse
import com.B17.sdssystem.developer.DatePicker
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import kotlinx.android.synthetic.main.project_dialog_fragment.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectDialog : DialogFragment() {

    val logger = AnkoLogger("ProjectDialog")

    public interface DialogListener{ fun onFinishDialog(inputText : String)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.project_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dialog.setTitle("Create New Project")
        tv_project_name.requestFocus()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        button.setOnClickListener { sendResult() }

        btn_start.setOnClickListener{showDatePicker(0)}
        btn_end.setOnClickListener{showDatePicker(1)}
    }


    public fun processDatePickerResult(year: Int, month: Int, day: Int, tag: String) {


        val month_string = (month + 1).toString()
        val day_string = day.toString()
        val year_string = year.toString()

        val date =  year_string + "-" + month_string + "-" + day_string



        if (tag.equals("0")) {
            tv_start_date.setText(date)
        }










        else if (tag.equals("1")) {
            tv_end_day.setText(date)
        }
    }
    fun showDatePicker(tag: Int) {
        val newFragment = DatePicker(this)











        val args = Bundle()
        args.putString("tag", tag.toString())
        newFragment.arguments = args
        newFragment.show(activity?.supportFragmentManager, "datePicker" )

    }

    fun sendResult() {

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        val createCall = apiInterface.createProject(tv_project_name.text.toString(), tv_project_status.text.toString(), tv_project_desc.text.toString(), tv_start_date.text.toString(), tv_end_day.text.toString())
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