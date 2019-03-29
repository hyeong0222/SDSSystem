package com.B17.sdssystem.developer.developertask

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.B17.sdssystem.data.TaskDetail
import com.B17.sdssystem.data.TaskListResponse
import com.B17.sdssystem.data.UpdateTask
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskListViewModel : ViewModel(), AnkoLogger {


    var taskdetail : MutableLiveData<TaskDetail> = MutableLiveData<TaskDetail>()
    var taskListResponse : MutableLiveData<TaskListResponse> = MutableLiveData<TaskListResponse>()
    var updateTaskresponse : MutableLiveData<UpdateTask> = MutableLiveData<UpdateTask>()


     fun getTaskDetails(task_id: String, project_id: String) : MutableLiveData<TaskDetail> {
        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        var task = apiInterface.viewTask(task_id,project_id)

        task.enqueue(object : Callback<TaskDetail> {

            override fun onResponse(call: Call<TaskDetail>, response: Response<TaskDetail>) {

                info {" TaskList Presenter "  + response.body()}
                taskdetail.value = response.body()
                //  subtaskFragView.getSubtaskList(response?.body().subtaskList)
            }

            override fun onFailure(call: Call<TaskDetail>?, t: Throwable?) {
                error { " TaskList Presenter " + t?.message }
            }
        })
          return taskdetail
    }


         fun requestTaskList(user_id: String) : MutableLiveData<TaskListResponse> {

         val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
         var taskList = apiInterface.viewTasks(user_id)

         taskList.enqueue(object : Callback<TaskListResponse> {

            override fun onResponse(call: Call<TaskListResponse>, response: Response<TaskListResponse>) {
                taskListResponse.value = response.body()
            }

            override fun onFailure(call: Call<TaskListResponse>?, t: Throwable?) {
                error { t?.message }
            }
        })
        return taskListResponse
    }

    fun updateTask(taskid : String, projectid : String, userid : String, task_status : String) : MutableLiveData<UpdateTask>{
        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
         var updateTaskCall = apiInterface.updateTask(taskid,projectid,userid,task_status)

        updateTaskCall.enqueue(object: Callback<UpdateTask> {

            override fun onResponse(call: Call<UpdateTask>, response: Response<UpdateTask>) {
                updateTaskresponse.value = response.body()
            }

            override fun onFailure(call: Call<UpdateTask>?, t: Throwable?) {
                error { t?.message }
            }
        })

          return updateTaskresponse
    }
}