package com.B17.sdssystem.project

import com.B17.sdssystem.data.entries.Project



















interface ProjectContract {
    interface View{ fun setAdapter(projects: List<Project>?)}

    interface Presenter{
        fun getProjects()
    }
}