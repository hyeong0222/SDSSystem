package com.B17.sdssystem.network

import com.B17.sdssystem.data.ResponseCreateTask
import com.B17.sdssystem.data.ResponseTask
import com.B17.sdssystem.data.Task
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("pms_create_task.php")
    fun createTask(@Query("project_id") project_id : String,
                   @Query("task_name") task_name : String,
                   @Query("task_status") task_status : String,
                   @Query("task_desc") task_desc : String,
                   @Query("start_date") start_date : String,
                   @Query("end_date") end_date : String) : Call<ResponseCreateTask>

    @GET("pms_project_task_list.php")
    fun getAllTasks() : Call<ResponseTask>
}