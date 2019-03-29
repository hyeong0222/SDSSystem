package com.B17.sdssystem.manager.project

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import com.B17.sdssystem.manager.project.ProjectDialog
import com.B17.sdssystem.manager.project.ProjectFragment
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import java.util.*

class DatePicker: DialogFragment(), DatePickerDialog.OnDateSetListener, AnkoLogger {

    //private lateinit var projectFragment: ProjectFragment

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val projectDialog : ProjectDialog = ProjectDialog()
        val tag = arguments!!.getString("tag")
        error { "Sang ---> " + year + " " + month + " " + dayOfMonth}
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