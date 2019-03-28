package com.B17.sdssystem.network

import com.B17.sdssystem.data.ResponseCreateTask
import com.B17.sdssystem.data.ResponseTask
import com.B17.sdssystem.data.Task
import com.B17.sdssystem.data.entries.ProjectResponse
import com.B17.sdssystem.data.Login
import com.B17.sdssystem.data.Msg
import com.B17.sdssystem.data.entries.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("pms_projects.php")
    fun getProjects() : Observable<ProjectResponse>

    @GET("pms_create_project.php")
    fun createProject(@Query("project_name") project_name : String,
                      @Query("project_status") project_status : String,
                      @Query("project_desc") project_desc : String,
                      @Query("start_date") start_date : String,
                      @Query("end_date") end_date : String) : Call<CreateProjectResponse>
    @GET("pms_view_subtask.php")
    fun getSubtasks(@Query("user_id") user_id : String, @Query("taskid") taskid : String) : Call<SubTaskResponse>

    @GET("pms_view_sub_task_deatil.php")
    fun getSubtasksDetails(@Query("taskid") taskid : String, @Query("subtask_id") subtask_id : String, @Query("project_id") project_id : String) : Call<SubTasksDetailResponse>

    @GET("pms_create_project_team.php")
    fun assign(@Query("project_id") project_id : String, @Query("team_member_userid") team_member_userid : String, @Query("task_id") task_id : String, @Query("subtask_id") subtask_id : String) : Call<AssignResponse>

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

    @GET("pms_employee_list.php")
    fun getEmployeeList() : Call<ResponseEmployeeList>

}