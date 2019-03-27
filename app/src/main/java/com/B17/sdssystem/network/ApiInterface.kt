package com.B17.sdssystem.network

import com.B17.sdssystem.data.entries.CreateProjectResponse
import com.B17.sdssystem.data.entries.ProjectResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {





    @GET("pms_projects.php")
    fun getProjects() : Observable<ProjectResponse>





    @GET("pms_create_project.php")
    fun createProject(








                    @Query("project_name") project_name : String,




                      @Query("project_status") project_status : String,

                      @Query("project_desc") project_desc : String,
                      @Query("start_date") start_date : String,

                      @Query("end_date") end_date : String) : Call<CreateProjectResponse>



















}