package com.B17.sdssystem.manager.assignment.assign

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.B17.sdssystem.data.entries.AssignResponse
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AssignViewModel : ViewModel() {
    val logger = AnkoLogger("AssignViewModel")

    var assignResponse : MutableLiveData<AssignResponse> = MutableLiveData()
    fun assign(project_id : String, user_id: String, task_id: String, subtasks_id : String) : LiveData<AssignResponse>{

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        val assignCall = apiInterface.assign(project_id, user_id, task_id, subtasks_id)

        assignCall.enqueue(object : Callback<AssignResponse> {
            override fun onFailure(call: Call<AssignResponse>, t: Throwable) {
                logger.info { t.message }

            }
            override fun onResponse(call: Call<AssignResponse>, response: Response<AssignResponse>) {
                logger.info { response.body() }

                assignResponse.value = response.body()
            }
        })

        return assignResponse
    }
}