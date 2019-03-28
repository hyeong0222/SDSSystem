package com.B17.sdssystem.developer.developertask

import com.B17.sdssystem.data.TaskList

interface TaskListContract {

    interface View{
        fun  returnTaskList(taskList : List<TaskList>)
    }

    interface Presenter{

        fun requestTaskList( user_id : String)
        fun getTaskDetails(task_id: String, project_id: String )
    }
}