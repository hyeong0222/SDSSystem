package com.B17.sdssystem.viewsubtask

import com.B17.sdssystem.data.entries.SubTaskResponse
import com.B17.sdssystem.data.entries.SubTasksDetailResponse
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response









class ViewsubtaskPresenter(val view : ViewsubtaskContract.View) : ViewsubtaskContract.Presenter{


    val logger = AnkoLogger("ViewsubtaskPresenter")
    val retrofitInstance = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)





    override fun getSubtasks() {





        val subTasksDetails = ArrayList<SubTasksDetailResponse>()


        val subtasksCall = retrofitInstance.getSubtasks("65", "112")
        subtasksCall.enqueue(object : Callback<SubTaskResponse>{
            override fun onFailure(call: Call<SubTaskResponse>, t: Throwable) {
                logger.info { t.message }
            }

            override fun onResponse(call: Call<SubTaskResponse>, response: Response<SubTaskResponse>) {

                logger.info{response.body()?.viewsubtasks}
                val subtasks = response.body()!!.viewsubtasks
                val size = subtasks?.size
                for (v in subtasks.orEmpty()) {



                    val subtasksdetailsCall = retrofitInstance.getSubtasksDetails(v.taskid!!, v.subtaskid!!, v.projectid!!)
                    subtasksdetailsCall.enqueue(object : Callback<SubTasksDetailResponse> {
                        override fun onFailure(call: Call<SubTasksDetailResponse>, t: Throwable) {
                            logger.info { t.message }
                        }

                        override fun onResponse(
                            call: Call<SubTasksDetailResponse>,
                            response: Response<SubTasksDetailResponse>
                        ) {
                            logger.info { response.body() }
                            subTasksDetails.add(response?.body()!!)











                            if (subTasksDetails.size == size) view.setAdapter(subTasksDetails)
                        }

                    })
                }
            }

        })
    }
}