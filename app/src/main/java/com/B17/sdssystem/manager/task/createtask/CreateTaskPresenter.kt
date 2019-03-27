package com.B17.sdssystem.manager.task.createtask

import com.B17.sdssystem.data.ResponseCreateTask
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateTaskPresenter (var view : CreateTaskContract.View) : CreateTaskContract.Presenter, AnkoLogger {


    override fun sendCreateTaskRequest(project_id: String, task_name: String, task_status: String,
                                       task_desc: String, start_date: String, end_date: String) {

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        var createTaskCall = apiInterface.createTask(project_id, task_name, task_status, task_desc, start_date, end_date)

        createTaskCall.enqueue(object : Callback<ResponseCreateTask> {
            override fun onResponse(call: Call<ResponseCreateTask>?, response: Response<ResponseCreateTask>?) {
                info { response?.body() }
                view.getCreateTaskResponse(response)
            }
            override fun onFailure(call: Call<ResponseCreateTask>?, t: Throwable?) {
                error { t?.message }
            }
        })
    }
}