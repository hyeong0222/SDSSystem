package com.B17.sdssystem.project

import com.B17.sdssystem.data.entries.ProjectResponse
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectPresenter(val view : ProjectContract.View) : ProjectContract.Presenter{


    val logger = AnkoLogger(this.javaClass.simpleName)
    override fun getProjects() {


        val apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val projectCall = apiInterface.getProjects()
        projectCall.enqueue(object : Callback<ProjectResponse> {
            override fun onResponse(call: Call<ProjectResponse>?, response: Response<ProjectResponse>?) {
                logger.info { response?.body().toString() }
                view.setAdapter(response?.body()?.projects)
            }

            override fun onFailure(call: Call<ProjectResponse>?, t: Throwable?) {

                logger.info { t?.message }
            }
        })
    }
}