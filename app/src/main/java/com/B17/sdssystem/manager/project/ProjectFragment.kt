package com.B17.sdssystem.project

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.B17.sdssystem.R
import com.B17.sdssystem.adapter.ProjectAdapter
import com.B17.sdssystem.data.entries.Project
import kotlinx.android.synthetic.main.project_fragment.*

class ProjectFragment : Fragment(), ProjectContract.View {












    override fun setAdapter(projects: List<Project>?) {




        rvProject.adapter = ProjectAdapter(projects)






    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvProject.layoutManager = LinearLayoutManager(context)
    }
    val presenter : ProjectPresenter by lazy {ProjectPresenter(this)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.project_fragment, container, false)

        presenter.getProjects()



        return view


    }
}