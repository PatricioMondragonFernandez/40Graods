package com.actin.app40Grados
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import app40grados.R
import java.util.*

class DatePickerFragment(val listener:(day:Int, month:Int, year: Int) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onDateSet(p0: DatePicker?, dayofMonth: Int, month: Int, year: Int) {
        listener(dayofMonth, month, year)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val picker = DatePickerDialog(activity as Context, R.style.datePickerTheme,
            this, day , month , year)
        picker.datePicker.minDate = c.timeInMillis
        c.add(Calendar.DAY_OF_MONTH, +7)
        picker.datePicker.maxDate = c.timeInMillis

        return picker
    }
}