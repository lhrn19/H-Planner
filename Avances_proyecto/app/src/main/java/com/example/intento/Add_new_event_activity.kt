package com.example.intento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
//import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Add_new_event_activity : AppCompatActivity() {

//Botones para los días
    private var m_presionado:Boolean = true
    private var l_presionado:Boolean = true
    private var mie_presionado:Boolean = true
    private var j_presionado:Boolean = true
    private var v_presionado:Boolean = true
    private var sa_presionado:Boolean = true



    private var do_presionado:Boolean = true

    private var from_hora:String = "00:00"
    private var to_hora:String = "00:00"

    private var everyweek_presionado:Boolean = false
    private var everymonth_presionado:Boolean = false

    //variables para comoo tal tomar el objeto input, se declara cual es cual en la funcion de abajo
    private lateinit var nombre_evento: EditText
    private  lateinit var calendario_evento: EditText
    private  lateinit var recordatorio_modo_evento: EditText
    private  lateinit var tiempo_reminder_evento: EditText
    private lateinit var everyweek_evento:EditText



    //para el calendario
    private  lateinit var  from_event: EditText
    private  lateinit var  to_event: EditText

    //bases de datos
    private lateinit var dbRef: DatabaseReference








    //---------------------------------------------------------------------función para añadir el evento
    private fun saveNewEvent(allDaySwitch: SwitchCompat,l_presionado:Boolean,m_presionado:Boolean,mie_presionado:Boolean,j_presionado:Boolean,v_presionado:Boolean,sa_presionado:Boolean,do_presionado:Boolean) {
        //Esta ya toma como tal los valores de los input
        var nombre_ingresado = nombre_evento.text.toString()
        var calendario_ingresado = calendario_evento.text.toString()
        var reminder_frequency_ingresado = everyweek_evento.text.toString()
        var reminder_tiempo_ingresado = tiempo_reminder_evento.text.toString()
        var n_reminder_tiempo_ingresado:Int = 0
        var allDay_ingresado = allDaySwitch.isChecked
        var dbRef_calendars = FirebaseDatabase.getInstance().getReference("calendar")




        if (nombre_ingresado.isEmpty()) {
            nombre_evento.error = "Please enter a name for the new event"
        }
        if (calendario_ingresado.isEmpty()) {
            calendario_evento.error = "Please enter a calendar for the new event"
        }
        if (calendario_ingresado.isEmpty()) {
            calendario_evento.error = "Please enter a calendar for the new event"
        }
        if (reminder_tiempo_ingresado.isNotEmpty()) {
            n_reminder_tiempo_ingresado = reminder_tiempo_ingresado.toInt()
        }
        if (reminder_frequency_ingresado.isEmpty()) {
            reminder_frequency_ingresado = "none"
        }
        val aux_id: String = when (calendario_ingresado.lowercase()) {
            "personal" -> "-NxoOkxEDldQT5k1baPt"
            "family" -> "-NxoOkxJqjiIWcJPdBng"
            "work" -> "-NxoOkxK8oqtmu3QsUZL"
            else -> "-NxoOkxLgPqacCT5YDQx"}



        //identificarlso por un id unico
        val eventId = dbRef.push().key!!
        //obejto del evento


        val evento = Evento(eventId,nombre_ingresado, calendario_ingresado, n_reminder_tiempo_ingresado,allDay_ingresado,reminder_frequency_ingresado,from_hora,to_hora,!l_presionado,!m_presionado,!mie_presionado,!j_presionado,!v_presionado,!sa_presionado,!do_presionado,"started")
        dbRef.child(eventId).setValue(evento)
        dbRef_calendars.child(aux_id).child("events").child(eventId).setValue("true")
            .addOnCompleteListener{ calendarTask ->
                if (calendarTask.isSuccessful) {
                    Toast.makeText(this@Add_new_event_activity, "Data inserted successfully", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@Add_new_event_activity, "Error inserting event ID into calendar: ${calendarTask.exception?.message}", Toast.LENGTH_LONG).show()
                }

            }.addOnFailureListener{err ->
                Toast.makeText( this, "Error${err.message}", Toast.LENGTH_LONG).show()
            }




    }





//-----------------------------------------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_event)
       //----------------------------varibales
        val lunes: ImageButton = findViewById(R.id.monday)
        val martes: ImageButton = findViewById(R.id.tuesday)
        val mierc: ImageButton = findViewById(R.id.wednesday)
        val jueves: ImageButton = findViewById(R.id.thursday)
        val vier: ImageButton = findViewById(R.id.friday)
        val sab: ImageButton = findViewById(R.id.saturday)
        val domin: ImageButton = findViewById(R.id.sunday)
        //------------------------------------para la fecha
        from_event = findViewById(R.id.from_date)
        to_event = findViewById(R.id.to_date)
        everyweek_evento = findViewById(R.id.reminder_frequency)

    //---------------------------------------------------para el calendario
    var calendars_names = listOf("Personal","Work","Family","Others")

        val autocomplete: AutoCompleteTextView = findViewById(R.id.calendario_input)
        val adapter = ArrayAdapter<String>(this, R.layout.list_item, calendars_names)
        autocomplete.setAdapter(adapter)
        autocomplete.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, i, _ ->
            val itemSelected = adapterView.getItemAtPosition(i)

        }

    //---------------------------------------------------para el reminder frequency
    var reminder_names = listOf("Every day","Every week", "Every month","None")
    val autocomplete2: AutoCompleteTextView = findViewById(R.id.reminder_frequency)
    val adapter2 = ArrayAdapter<String>(this, R.layout.list_item2, reminder_names)
    autocomplete2.setAdapter(adapter2)
    autocomplete2.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, i, _ ->
        val itemSelected = adapterView.getItemAtPosition(i)


    }



    //------------------------------------cerrar
    val discard_new_event: ImageButton = findViewById(R.id.back)
    discard_new_event.setOnClickListener {

      //  Toast.makeText(this, "The new event was not saved", Toast.LENGTH_SHORT).show()
        val intent_discard = Intent(this, MainActivity::class.java)
        startActivity(intent_discard)

    }
    // ---------------------------------------------------Tomar los inputs
    nombre_evento = findViewById(R.id.nombre_input)
    calendario_evento = findViewById(R.id.calendario_input)
    tiempo_reminder_evento = findViewById(R.id.reminder_minutes_input)

    //---------------------------------------------------Tomar los switches
    val allDaySwitch:SwitchCompat = findViewById(R.id.switch_allday)







    //----------------------------------------------------Boton de guardar
    val save_new_event: Button = findViewById(R.id.save)

    //---------------------------------------------------Llamar base de datos


    dbRef = FirebaseDatabase.getInstance().getReference("new_event")


    //-----------------------------------------------------funciones calendario

    from_event.setOnClickListener {
        showDatePicker() }
    to_event.setOnClickListener {
        showDatePicker_2() }


    //-----------------------------------------------------funciones para botones
    save_new_event.setOnClickListener {


        saveNewEvent(allDaySwitch,l_presionado,m_presionado,mie_presionado,j_presionado,v_presionado,sa_presionado,do_presionado)
        val intent_upcoming = Intent(this, MainActivity::class.java)
        startActivity(intent_upcoming)




    }




    //-----------------------------------cambiar el icono cuando se presiona el día
        lunes.setOnClickListener {
            if (l_presionado) {
                lunes.setImageResource(R.drawable.m_l)
                l_presionado = false
            } else {

                lunes.setImageResource(R.drawable.m)
                l_presionado = true
            }
        }
            martes.setOnClickListener {
                if (m_presionado) {
                    martes.setImageResource(R.drawable.t_l)
                    m_presionado = false
                } else {
                    martes.setImageResource(R.drawable.t)
                    m_presionado = true
                }
            }

            mierc.setOnClickListener {
                if (mie_presionado) {
                    mierc.setImageResource(R.drawable.w_p)
                    mie_presionado = false
                } else {
                    mierc.setImageResource(R.drawable.w)
                    mie_presionado = true
                }
            }

            jueves.setOnClickListener {
                if (j_presionado) {
                    jueves.setImageResource(R.drawable.t_l)
                    j_presionado = false
                } else {
                    jueves.setImageResource(R.drawable.t)
                    j_presionado = true
                }
            }

            vier.setOnClickListener {
                if (v_presionado) {
                    vier.setImageResource(R.drawable.f_l)
                    v_presionado = false
                } else {
                    vier.setImageResource(R.drawable.f)
                    v_presionado = true
                }
            }

            sab.setOnClickListener {
                if (sa_presionado) {
                    sab.setImageResource(R.drawable.s_l)
                    sa_presionado = false
                } else {
                    sab.setImageResource(R.drawable.s)
                    sa_presionado = true
                }
            }

            domin.setOnClickListener {
                if (do_presionado) {
                    domin.setImageResource(R.drawable.s_l)
                    do_presionado = false
                } else {
                    domin.setImageResource(R.drawable.s)
                    do_presionado = true
                }
            }






    }

    //---------------------------------------------------Funciones llamadas para crear el selector de tiempo
    private fun showDatePicker(){
        val timePicker = TimePickerFragment{fechaSeleccioanda(it)}
        timePicker.show(supportFragmentManager,"timePicker")

    }
    private fun fechaSeleccioanda(time:String) {
        from_hora = time
        from_event.setText(time)
    }
    private fun showDatePicker_2(){
            val timePicker = TimePickerFragment{fechaSeleccioanda_2(it)}
            timePicker.show(supportFragmentManager,"timePicker")

        }
    private fun fechaSeleccioanda_2(time:String) {
        to_hora = time
        to_event.setText(time)


    }
}