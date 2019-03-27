package com.B17.sdssystem.manager.assignment.employeelist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.B17.sdssystem.data.EmployeeList
import com.B17.sdssystem.data.ResponseEmployeeList
import com.B17.sdssystem.network.ApiInterface
import com.B17.sdssystem.network.RetrofitInstance
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeListViewModel : ViewModel(), AnkoLogger {

    val employeeList : MutableLiveData<List<EmployeeList>> = MutableLiveData<List<EmployeeList>>()

    fun sendEmployeeListRequest() : MutableLiveData<List<EmployeeList>> {
        val apiInterface = RetrofitInstance().getRetrofitInstance().create(ApiInterface::class.java)
        var employeeListCall = apiInterface.getEmployeeList()

        employeeListCall.enqueue(object : Callback<ResponseEmployeeList> {
            override fun onResponse(call: Call<ResponseEmployeeList>?, response: Response<ResponseEmployeeList>?) {
                info { response?.body() }
                employeeList.postValue(response?.body()?.getEmployeeList)
            }
            override fun onFailure(call: Call<ResponseEmployeeList>?, t: Throwable?) {
                error { t?.message }
            }
        })

        return employeeList
    }
}