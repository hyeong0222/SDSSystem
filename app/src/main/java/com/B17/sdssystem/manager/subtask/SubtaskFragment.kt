package com.B17.sdssystem.manager.subtask


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
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

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SubtaskFragment : Fragment(), AnkoLogger {

    private var TAG : String = "SubtaskFragment"
    private var subtasks : ArrayList<Subtask> = ArrayList<Subtask>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater.inflate(R.layout.fragment_sub_task, container, false)

        //setting RecyclerView
        var recyclerView = v.findViewById<RecyclerView>(R.id.rv_subtaskFrag)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        val mPreferences = activity!!.getSharedPreferences("MANAGER", Context.MODE_PRIVATE)
        var taskID : String? = mPreferences.getString("task", null)

        val viewModel : SubtaskViewModel = ViewModelProviders.of(this).get(SubtaskViewModel::class.java)
        var subtaskList : LiveData<SubtaskResponse>? = viewModel.getSubtaskList()
        subtaskList?.observe(this, Observer { s ->
            val subtask : List<Subtask> = s!!.subtaskList
            for (i in 0..subtask.size-1) {
                if (subtask.get(i).taskid.equals(taskID)){
                    subtasks.add(subtask.get(i))
                }
            }
            recyclerView.adapter = SubTaskAdapter(subtasks)
        })
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        floatingActionButton.setOnClickListener {
            showEditDialog()
        }

    }

    private fun showEditDialog() {

        val fm = activity!!.supportFragmentManager
        var fg = SubtaskDialogFragment()

        //if(fg.isDetached){ activity!!.supportFragmentManager.beginTransaction().attach(this).commitAllowingStateLoss()}
        info { "---> checking if show edit dialog method is called" }
        fg.setTargetFragment(this, 400)

       fg.show(fm, "New SubTask")
    }

}
