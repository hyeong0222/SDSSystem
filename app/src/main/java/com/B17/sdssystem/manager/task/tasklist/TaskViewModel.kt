package com.B17.sdssystem.manager.task.tasklist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.B17.sdssystem.data.ResponseTask
import com.B17.sdssystem.data.Task
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskViewModel : ViewModel(), AnkoLogger {

    val taskList : MutableLiveData<List<Task>> = MutableLiveData<List<Task>>()

    fun sendTaskRequest() : MutableLiveData<List<Task>> {
        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        var tasksCall = apiInterface.getAllTasks()

        tasksCall.enqueue(object : Callback<ResponseTask> {
            override fun onResponse(call: Call<ResponseTask>?, response: Response<ResponseTask>?) {
                info { response?.body() }
                taskList.postValue(response?.body()?.taskList)
            }
            override fun onFailure(call: Call<ResponseTask>?, t: Throwable?) {
                error { t?.message }
            }
        })

        return taskList
    }
}