package com.B17.sdssystem.developer

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import com.B17.sdssystem.manager.project.ProjectDialog
import com.B17.sdssystem.manager.project.ProjectFragment
import java.util.*

class DatePicker (val projectDialog : ProjectDialog): DialogFragment(), DatePickerDialog.OnDateSetListener {


    //private lateinit var projectFragment: ProjectFragment

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {



        val tag = arguments!!.getString("tag")
        projectDialog.processDatePickerResult(year, month, dayOfMonth, tag)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)

        val month = c.get(Calendar.MONTH)




        val day = c.get(Calendar.DAY_OF_MONTH)


        val date = DatePickerDialog(activity, this, year, month, day)








        date.datePicker.minDate = (System.currentTimeMillis())
        return date
    }
}