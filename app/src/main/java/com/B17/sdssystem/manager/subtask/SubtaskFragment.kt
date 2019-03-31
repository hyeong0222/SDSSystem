package com.B17.sdssystem.manager.subtask


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.B17.sdssystem.R
import com.B17.sdssystem.adapter.SubTaskAdapter
import com.B17.sdssystem.data.ResponseCreateSubtask
import com.B17.sdssystem.data.Subtask
import com.B17.sdssystem.data.SubtaskResponse
import kotlinx.android.synthetic.main.fragment_sub_task.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.error
import org.jetbrains.anko.info

//import com.B17.sdssystem.developer.R


class SubtaskFragment : Fragment(), AnkoLogger, DialogInterface.OnDismissListener {

    private var TAG : String = "SubtaskFragment"
    private var subtasks : ArrayList<Subtask> = ArrayList<Subtask>()
    private lateinit var subtaskAdapter : SubTaskAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater.inflate(R.layout.fragment_sub_task, container, false)

        info{ "onCreateView ---->"}

        //setting RecyclerView
        var recyclerView = v.findViewById<RecyclerView>(R.id.rv_subtaskFrag)
        var floatingActionButton = v.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        subtasks.clear()
        subtaskNetworkCall()
        subtaskAdapter = SubTaskAdapter(subtasks,context)
        recyclerView.adapter = subtaskAdapter

        floatingActionButton.setOnClickListener {
            showEditDialog()
        }

        return v
    }

    override fun onResume() {
        super.onResume()
        info{ "onResume ---->"}

        subtasks.clear()

        val d = SubtaskDialogFragment()
        d.setOnDismissListener(DialogInterface.OnDismissListener {
            subtaskNetworkCall()
            rv_subtaskFrag.removeAllViews()
            subtaskAdapter = SubTaskAdapter(subtasks,context)
            subtaskAdapter.notifyDataSetChanged()
            rv_subtaskFrag.adapter = subtaskAdapter
        })
        d.show(fragmentManager,"Dialog")
    }

    private fun showEditDialog() {

        val fm = activity!!.supportFragmentManager
        var fg = SubtaskDialogFragment()
        fg.setTargetFragment(this, 400)
        fg.show(fm, "New SubTask")

    }

    private fun subtaskNetworkCall(){

        val mPreferences = activity!!.getSharedPreferences("MANAGER", Context.MODE_PRIVATE)
        var taskID : String? = mPreferences.getString("task", null)

        val viewModel : SubtaskViewModel = ViewModelProviders.of(this).get(SubtaskViewModel::class.java)
        var subtaskList : LiveData<SubtaskResponse>? = viewModel.getSubtaskList()
        subtaskList?.observe(this, Observer { s ->
            subtasks.clear()
            val subtask : List<Subtask> = s!!.subtaskList
            for (i in 0..subtask.size-1) {
                if (subtask.get(i).taskid.equals(taskID)){
                    subtasks.add(subtask.get(i))
                }
            }
        })

    }

    override fun onDismiss(dialog: DialogInterface?) {
        error { "-------------------------- Dialog Dismiss Check" }
    }

}
