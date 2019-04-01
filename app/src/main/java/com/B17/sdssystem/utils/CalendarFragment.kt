package com.B17.sdssystem.utils


import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import com.B17.sdssystem.R
import com.B17.sdssystem.manager.project.ProjectDialog

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CalendarFragment : DialogFragment(), DatePickerDialog.OnDateSetListener, AnkoLogger {

    private var TAG = "Calendar Fragment"
    lateinit var calendar : Calendar

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)

        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        view?.minDate = System.currentTimeMillis()
        // Formatting time in correct form
        var formatData = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(calendar.time)
        if (calendar.timeInMillis < System.currentTimeMillis()) {

            Toast.makeText(context, "Please Select Date after Today", Toast.LENGTH_LONG).show()
        } else {
            error { TAG + formatData }

            targetFragment?.onActivityResult(
                targetRequestCode,
                Activity.RESULT_OK,
                Intent().putExtra("formatData", formatData)
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)













        var datePicker = DatePickerDialog(activity, this@CalendarFragment, year, month, day)
        datePicker.datePicker.minDate = System.currentTimeMillis()

        return datePicker
    }


}
