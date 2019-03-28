package com.B17.sdssystem.developer.viewsubtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.B17.sdssystem.R
import com.B17.sdssystem.adapter.SubTasksDetailsAdapter
import com.B17.sdssystem.data.entries.SubTasksDetailResponse
import com.B17.sdssystem.developer.viewsubtask.SubTasksDetailFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_viewsubtask.*

class ViewsubtaskFragment : Fragment(), ViewsubtaskContract.View, SubTasksDetailsAdapter.OnItemClickListener {





    private lateinit var adapter :SubTasksDetailsAdapter
    override fun onItemClick(view: View, position: Int) {
        val gson = Gson()
        val json = gson.toJson(adapter.subtasksDetails.get(position))






        val editor = activity!!.getSharedPreferences("default", Context.MODE_PRIVATE).edit()
        editor.putString("subtasks", json).apply()

        val args = Bundle()
        args.putString("subtasks", json)
        val subTasksDetailsFragment = SubTasksDetailFragment()



        subTasksDetailsFragment.arguments = args






        activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment, subTasksDetailsFragment)
           .addToBackStack(null).commit()
    }

    override fun setAdapter(subtasksdetails: List<SubTasksDetailResponse>) {






        adapter = SubTasksDetailsAdapter(subtasksdetails)
        rv_subtask.adapter = adapter
        adapter.onItemClickListener = this
    }


    val presenter = ViewsubtaskPresenter(this)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        return inflater.inflate(R.layout.fragment_viewsubtask, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        rv_subtask.layoutManager = LinearLayoutManager(context)


        presenter.getSubtasks()
    }
}
