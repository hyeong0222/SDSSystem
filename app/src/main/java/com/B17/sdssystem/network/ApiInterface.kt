package com.B17.sdssystem.network

import com.B17.sdssystem.data.entries.ProjectResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("pms_projects.php")
    fun getProjects() : Call<ProjectResponse>














}