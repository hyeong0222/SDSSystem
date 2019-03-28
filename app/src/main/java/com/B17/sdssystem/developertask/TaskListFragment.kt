package com.B17.sdssystem.developertask


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.B17.sdssystem.R
import com.B17.sdssystem.data.TaskList
import org.jetbrains.anko.AnkoLogger

//import com.B17.sdssystem.developer.R


/**
 * A simple [Fragment] subclass.
 *
 */
class TaskListFragment : Fragment(), AnkoLogger, TaskListContract.View {
    override fun returnTaskList(taskList: List<TaskList>) {
    }

    private val taskListPresenter  = TaskListPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var v = inflater.inflate(R.layout.fragment_task_list, container, false)

         /* testing mock data.
        taskListPresenter.requestTaskList("53")*/

        taskListPresenter.getTaskDetails("112","203")


        return v
    }


}
