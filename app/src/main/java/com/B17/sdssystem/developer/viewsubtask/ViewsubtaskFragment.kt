package com.B17.sdssystem.developer.viewsubtask

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.B17.sdssystem.R
import com.B17.sdssystem.adapter.SubTasksDetailsAdapter
import com.B17.sdssystem.data.entries.SubTasksDetailResponse
import kotlinx.android.synthetic.main.fragment_viewsubtask.*

class ViewsubtaskFragment : Fragment(), ViewsubtaskContract.View {
    override fun setAdapter(subtasksdetails: List<SubTasksDetailResponse>) {






        val adapter = SubTasksDetailsAdapter(subtasksdetails)
        rv_subtask.adapter = adapter
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
