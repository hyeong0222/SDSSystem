package com.B17.sdssystem.developer.viewsubtask

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.B17.sdssystem.data.UpdateSubtask
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubTasksDetailViewModel : ViewModel() {








    val logger = AnkoLogger("SubTasksDetail")

    var subtasksResponse = MutableLiveData<UpdateSubtask>()


    fun updateSubTasks(subid: String, tid: String, pid: String, uid: String, status : String) : MutableLiveData<UpdateSubtask> {

        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)

        val subtasksCall = apiInterface.updateSubtask(tid, subid, pid, uid, status)
        subtasksCall.enqueue(object : Callback<UpdateSubtask>{
            override fun onFailure(call: Call<UpdateSubtask>, t: Throwable) {







                logger.error { t.message }
            }

            override fun onResponse(call: Call<UpdateSubtask>, response: Response<UpdateSubtask>) {
                logger.info { response.body() }
                subtasksResponse.value = response.body()
            }

        })



        return subtasksResponse


    }


}