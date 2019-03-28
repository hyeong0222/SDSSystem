package com.B17.sdssystem.manager.project

import com.B17.sdssystem.data.entries.Project
import com.B17.sdssystem.data.entries.ProjectResponse
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectPresenter(val view : ProjectContract.View) : ProjectContract.Presenter {

    val logger = AnkoLogger(this.javaClass.simpleName)
    override fun getProjects() {

//        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
//        val projectCall = apiInterface.getProjects()
//        projectCall.enqueue(object : Callback<ProjectResponse> {
//            override fun onResponse(call: Call<ProjectResponse>?, response: Response<ProjectResponse>?) {
//                logger.info { response?.body().toString() }
//                view.setAdapter(response?.body()?.projects)
//            }

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        val projectObservable = apiInterface.getProjects()
        val subscribe = projectObservable
            .map{it -> it.projects}


            .flatMapIterable {it -> it}
            .filter{it -> it.projectstatus.equals("1")}
            //.map(it.filter {response: Project -> response.projectstatus.equals("1")})


            .toList()
            .toObservable()

            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResult, this::handleError)
        // val projectCall = apiInterface.getProjects()
//        projectCall.enqueue(object : Callback<ProjectResponse> {
//            override fun onResponse(call: Call<ProjectResponse>?, response: Response<ProjectResponse>?) {
//                logger.info { response?.body().toString() }
//                view.setAdapter(response?.body()?.projects)
//            }
//
//            override fun onFailure(call: Call<ProjectResponse>?, t: Throwable?) {
//
//                logger.info { t?.message }
//            }
//
//        })


//            override fun onFailure(call: Call<ProjectResponse>?, t: Throwable?) {
//                logger.error { t?.message }
//            }
//        })

    }private fun handleResult(response: List<Project>) {

        logger.info { response.toString() }
                view.setAdapter(response)
    }





    private fun handleError(throwable : Throwable) {
        logger.info { throwable.message }
    }
}