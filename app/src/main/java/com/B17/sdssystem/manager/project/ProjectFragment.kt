package com.B17.sdssystem.manager.project

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.B17.sdssystem.R
import com.B17.sdssystem.adapter.ProjectAdapter
import com.B17.sdssystem.data.entries.Project
import com.B17.sdssystem.developer.DatePicker
import com.B17.sdssystem.manager.task.TaskFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.project_fragment.*

class ProjectFragment : Fragment(), ProjectContract.View, ProjectDialog.DialogListener, ProjectAdapter.OnItemClickListener {
    lateinit var adapter : ProjectAdapter

    override fun onItemClick(view: View, position: Int) {

        val editor = activity!!.getSharedPreferences("MANAGER", Context.MODE_PRIVATE).edit()
        editor.putString("project", adapter.projects?.get(position)?.id).apply()







        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_managerActivity, TaskFragment())
            .addToBackStack(null).commit()
    }

    override fun onFinishDialog(inputText: String) {
        presenter.fetchProjects()

        Toast.makeText(activity, inputText, Toast.LENGTH_LONG).show()
    }

    override fun setAdapter(projects: List<Project>?) {

        adapter = ProjectAdapter(projects)
        adapter.onItemClickListener = this
        rvProject.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvProject.layoutManager = LinearLayoutManager(context)
        fab_project.setOnClickListener { showEditDialog() }
    }

    val presenter : ProjectPresenter by lazy {
        ProjectPresenter(
            this
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.project_fragment, container, false)
        presenter.getProjects()

        return view
    }

    private fun showEditDialog() {

        val fm = activity!!.supportFragmentManager
        val fg = ProjectDialog()

        fg.setTargetFragment(this, 300)
        fg.show(fm, "New Project")
    }

}