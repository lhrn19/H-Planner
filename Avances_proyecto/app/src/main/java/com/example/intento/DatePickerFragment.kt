package com.example.intento
//pasarle una funcion como input que a su vez recibe esos tres calores
import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment //Mostrar esos dialogos
import android.content.Context

class DatePickerFragment (val listener:(day:Int,mont:Int,year:Int)->Unit):DialogFragment(),
    DatePickerDialog.OnDateSetListener{


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth,month,year)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val hoy = Calendar.getInstance() //calendario
        val dia:Int = hoy.get(Calendar.DAY_OF_MONTH)
        val mes:Int = hoy.get(Calendar.MONTH)
        val ano:Int = hoy.get(Calendar.YEAR)

        val picker = DatePickerDialog(activity as Context,this, ano,mes,dia)
        return picker

    }


}