package com.B17.sdssystem.manager.subtask

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.B17.sdssystem.data.ResponseCreateSubtask
import com.B17.sdssystem.data.Subtask
import com.B17.sdssystem.data.SubtaskResponse
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubtaskViewModel : ViewModel(), AnkoLogger{

        var subtaskResponse: MutableLiveData<SubtaskResponse> = MutableLiveData<SubtaskResponse>()
        var responseCreateSubtask: MutableLiveData<ResponseCreateSubtask>? = MutableLiveData<ResponseCreateSubtask>()

    fun getSubtaskList() : MutableLiveData<SubtaskResponse>? {

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        var subtaskList = apiInterface.getSubTasks()

        subtaskList.enqueue(object : Callback<SubtaskResponse> {
            override fun onResponse(call: Call<SubtaskResponse>, response: Response<SubtaskResponse>) {
                subtaskResponse?.value = response.body()
            }
            override fun onFailure(call: Call<SubtaskResponse>?, t: Throwable?) {
                error { t?.message }
            }
        })
        return subtaskResponse
    }

    fun createSubtask( project_id: String,  task_id: String, sub_task_name: String, sub_task_status: String,  sub_task_desc: String,
                                start_date: String, end_date: String) : MutableLiveData<ResponseCreateSubtask>? {
        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        var createSubtask= apiInterface.createSubTask(project_id,task_id,sub_task_name,sub_task_status,sub_task_desc,
            start_date,end_date)

        createSubtask.enqueue(object : Callback<ResponseCreateSubtask> {
            override fun onResponse(call: Call<ResponseCreateSubtask>, response: Response<ResponseCreateSubtask>) {
                info { "--> Subtask ViewModel --->  " + response.body()?.msg?.get(0) + " " + response.body()?.sub_task_id.toString() + " " + response.body()?.project_id + " " + response.body()?.task_id }
                responseCreateSubtask!!.value = response.body()
            }
            override fun onFailure(call: Call<ResponseCreateSubtask>?, t: Throwable?) {
                error { t?.message }
            }
        })
        return responseCreateSubtask
    }

}