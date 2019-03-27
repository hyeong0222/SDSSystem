package com.B17.sdssystem.data

import com.google.gson.annotations.SerializedName

data class ResponseTask(@SerializedName("project task") var taskList : List<Task> = ArrayList<Task>()) {
}