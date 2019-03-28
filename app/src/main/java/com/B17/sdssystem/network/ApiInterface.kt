package com.B17.sdssystem.network

import com.B17.sdssystem.data.*
import com.B17.sdssystem.data.entries.ProjectResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("pms_projects.php")
    fun getProjects() : Call<ProjectResponse>

    @GET("pms_create_task.php")
    fun createTask(@Query("project_id") project_id : String,
                   @Query("task_name") task_name : String,
                   @Query("task_status") task_status : String,
                   @Query("task_desc") task_desc : String,
                   @Query("start_date") start_date : String,
                   @Query("end_date") end_date : String) : Call<ResponseCreateTask>

    @GET("pms_project_task_list.php")
    fun getAllTasks() : Call<ResponseTask>

    @GET("pms_reg.php")
    fun submitReg(@Query("first_name") fname: String,
                  @Query("last_name") lname : String,
                  @Query("email") email: String,
                  @Query("mobile") mobile: String,
                  @Query("password") password: String,
                  @Query("company_size") companySize: String,
                  @Query("your_role") role: String) : Call<Msg>

    @GET("pms_login.php")
    fun submitLogin( @Query("email") email: String,
                     @Query("password") password: String) : Call<Login>

    @GET("pms_create_sub_task.php")
    fun createSubTask(@Query("project_id") project_id : String,
                       @Query("task_id") task_id : String,
                       @Query("sub_task_name") sub_task_name : String,
                       @Query("sub_task_status") sub_task_status : String,
                       @Query("sub_task_desc") sub_task_desc : String,
                       @Query("start_date") start_date : String,
                       @Query("end_date") end_date : String) : Call<ResponseCreateSubtask>


     @GET("pms_project_sub_task_list.php")
     fun getSubTasks() : Call<SubtaskResponse>

     @GET("pms_view_task.php")
     fun viewTasks(@Query("user_id") user_id : String) : Call<TaskListResponse>

     @GET("pms_view_task_deatil.php")
     fun viewTask(@Query("taskid") taskid :String,
                  @Query("project_id") project_id :String ) : Call<TaskDetail>

}