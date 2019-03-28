package com.B17.sdssystem.developertask

import com.B17.sdssystem.data.TaskDetail
import com.B17.sdssystem.data.TaskListResponse
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskListPresenter(var taskListFragmentView: TaskListContract.View) : AnkoLogger, TaskListContract.Presenter {
    override fun getTaskDetails(task_id: String, project_id: String) {
        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        var task = apiInterface.viewTask(task_id,project_id)

        task.enqueue(object : Callback<TaskDetail> {

            override fun onResponse(call: Call<TaskDetail>, response: Response<TaskDetail>) {

                 info {" TaskList Presenter "  + response?.body()?.taskname}
                //  subtaskFragView.getSubtaskList(response?.body().subtaskList)
            }

            override fun onFailure(call: Call<TaskDetail>?, t: Throwable?) {
                info { t?.message }
            }
        })

    }

    override fun requestTaskList(user_id: String) {

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        var taskList = apiInterface.viewTasks(user_id)

        taskList.enqueue(object : Callback<TaskListResponse> {

            override fun onResponse(call: Call<TaskListResponse>, response: Response<TaskListResponse>) {

               // info {" TaskList Presenter"  + response?.body().taskList.get(0).subtaskid}
              //  subtaskFragView.getSubtaskList(response?.body().subtaskList)
            }

            override fun onFailure(call: Call<TaskListResponse>?, t: Throwable?) {
                info { t?.message }
            }
        })

    }


}