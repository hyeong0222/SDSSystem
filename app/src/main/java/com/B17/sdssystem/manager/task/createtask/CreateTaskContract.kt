package com.B17.sdssystem.manager.task.createtask

import com.B17.sdssystem.data.ResponseCreateTask
import retrofit2.Response

interface CreateTaskContract {

    interface View {
        fun getCreateTaskResponse(response: Response<ResponseCreateTask>?)
    }

    interface Presenter {
        fun sendCreateTaskRequest(project_id : String, task_name : String, task_status : String,
                                  task_desc : String, start_date : String, end_date : String)
    }
}