package com.B17.sdssystem.data

import com.google.gson.annotations.SerializedName

data class Task(var taskid : String, var projectid : String, var taskname : String, var taskstatus : String,
                var taskdesc : String, var startdate : String, var endstart : String) {
}