package com.B17.sdssystem.manager.task.tasklist

import com.B17.sdssystem.data.ResponseTask
import retrofit2.Response

interface TaskContract {
    interface View {
        fun getTaskResponse(response: Response<ResponseTask>?)
    }

    interface Presenter {
        fun sendTaskRequest()
    }
}