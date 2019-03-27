package com.B17.sdssystem.viewsubtask

import com.B17.sdssystem.data.entries.SubTasksDetailResponse

interface ViewsubtaskContract {



    interface View{ fun setAdapter(subtasksdetails : List<SubTasksDetailResponse>)}
    interface Presenter{ fun getSubtasks()}
}