package com.B17.sdssystem.data

import com.google.gson.annotations.SerializedName

data class ResponseEmployeeList(@SerializedName("employees") var getEmployeeList : List<EmployeeList> = ArrayList<EmployeeList>()){
}