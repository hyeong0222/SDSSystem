package com.B17.sdssystem.data

import com.google.gson.annotations.SerializedName

class TaskListResponse(@SerializedName("view tasks")var taskList : List<TaskList>){

}