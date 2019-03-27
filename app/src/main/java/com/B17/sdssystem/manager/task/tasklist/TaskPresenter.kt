package com.B17.sdssystem.manager.task.tasklist

import com.B17.sdssystem.data.ResponseTask
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskPresenter (var view : TaskContract.View) : TaskContract.Presenter, AnkoLogger {

    override fun sendTaskRequest() {
        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        var tasksCall = apiInterface.getAllTasks()

        tasksCall.enqueue(object : Callback<ResponseTask> {
            override fun onResponse(call: Call<ResponseTask>?, response: Response<ResponseTask>?) {
                info { response?.body() }
                view.getTaskResponse(response)
            }

            override fun onFailure(call: Call<ResponseTask>?, t: Throwable?) {
                error { t?.message }
            }
        })
    }
}