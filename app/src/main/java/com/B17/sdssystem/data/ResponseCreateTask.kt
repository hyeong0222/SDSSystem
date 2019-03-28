package com.B17.sdssystem.data

import com.google.gson.annotations.SerializedName

data class ResponseCreateTask(var msg :List<String>, var task_id : Int, var project_id : String) {
}