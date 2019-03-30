package com.B17.sdssystem.manager.assignment.assign

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.B17.sdssystem.data.entries.AssignResponse
import com.B17.sdssystem.data.entries.AssignTasksResponse
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AssignViewModel : ViewModel() {
    val logger = AnkoLogger("AssignViewModel")




    var assignSubTasksResponse : MutableLiveData<AssignTasksResponse> = MutableLiveData()
    var assignResponse : MutableLiveData<AssignResponse> = MutableLiveData()
    var assignTasksResponse : MutableLiveData<AssignTasksResponse> = MutableLiveData()
    fun assign(project_id : String, user_id: String, task_id: String, subtasks_id : String) : LiveData<AssignResponse>{

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        val assignCall = apiInterface.assign(project_id, user_id, task_id, subtasks_id)

        assignCall.enqueue(object : Callback<AssignResponse> {
            override fun onFailure(call: Call<AssignResponse>, t: Throwable) {
                logger.info { t.message }

            }
            override fun onResponse(call: Call<AssignResponse>, response: Response<AssignResponse>) {
                logger.info { response.body() }

                assignResponse.value = response.body()
            }


        })

        return assignResponse
    }




    fun assignSubTasks(project_id: String, task_id: String, subtasks_id : String, user_id: String) : LiveData<AssignTasksResponse> {

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)



        val assignsubTasksCall = apiInterface.assignSubTasks(subtasks_id, task_id, project_id, user_id)
        assignsubTasksCall.enqueue(object : Callback<AssignTasksResponse> {
            override fun onFailure(call: Call<AssignTasksResponse>, t: Throwable) {



                logger.error { t.message }
            }

            override fun onResponse(call: Call<AssignTasksResponse>, response: Response<AssignTasksResponse>) {
                logger.info{response.body()}


                assignSubTasksResponse.value = response.body()
            }
        })
        return assignSubTasksResponse
    }
    fun assignTasks(project_id: String, task_id: String, user_id: String) : LiveData<AssignTasksResponse> {

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)



        val assignTasksCall = apiInterface.assignTask(project_id, user_id, task_id)
        assignTasksCall.enqueue(object : Callback<AssignTasksResponse> {
            override fun onFailure(call: Call<AssignTasksResponse>, t: Throwable) {



                logger.error { t.message }
            }

            override fun onResponse(call: Call<AssignTasksResponse>, response: Response<AssignTasksResponse>) {
                logger.info{response.body()}


                assignTasksResponse.value = response.body()
            }
        })
        return assignTasksResponse
    }
}