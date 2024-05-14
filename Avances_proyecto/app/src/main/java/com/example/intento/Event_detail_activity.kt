package com.example.intento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.BatchUpdates
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.childEvents

class Event_detail_activity : AppCompatActivity() {


//Botones para los días

    private lateinit var from_hora: TextView
    private lateinit var to_hora: TextView
    private lateinit var nombre_evento: TextView
    private  lateinit var calendario_evento: TextView
    private  lateinit var id_evento: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnFinish: Button
    private  lateinit var  from_event_modify: EditText
    private  lateinit var  to_event_modify: EditText
    private var from_hora_m:String = "00:00"
    private var to_hora_m:String = "00:00"
    private lateinit var past_calendar:String
    private var m_presionado:Boolean = true
    private var l_presionado:Boolean = true
    private var mie_presionado:Boolean = true
    private var j_presionado:Boolean = true
    private var v_presionado:Boolean = true
    private var sa_presionado:Boolean = true
    private var do_presionado:Boolean = true

    private var from_hora_modify:String = "00:00"
    private var to_hora_modify:String = "00:00"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_details)
        val back: ImageButton = findViewById(R.id.back)


        back.setOnClickListener{
            val intent_discard = Intent(this, MainActivity::class.java)
            startActivity(intent_discard)

        }
        initView()
        setValuesToViews()
        past_calendar = intent.getStringExtra("nombre").toString()
        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("Id").toString(),
                intent.getStringExtra("nombre").toString()

            )
        }

        btnFinish.setOnClickListener{
            ChangeState(intent.getStringExtra("Id").toString(),"finished")
        }

        btnDelete.setOnClickListener{
            deleteRecord(intent.getStringExtra("Id").toString())
        }

    }

    private fun initView(){
        id_evento = findViewById(R.id.tvEventId)
        nombre_evento = findViewById(R.id.tvEventName) // Reemplaza R.id.nombre_evento con el ID correcto de tu TextView
        calendario_evento = findViewById(R.id.tvEventCalendar) // Reemplaza R.id.calendario_evento con el ID correcto de tu TextView
        from_hora = findViewById(R.id.tvEmpFrom) // Reemplaza R.id.from_hora con el ID correcto de tu TextView
        to_hora = findViewById(R.id.tvEmpTo)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnFinish = findViewById(R.id.btnFinish)
        btnDelete = findViewById(R.id.btnDelete)


    }
    private fun setValuesToViews(){
        id_evento.text =intent.getStringExtra("Id")
        nombre_evento.text =intent.getStringExtra("nombre")
        calendario_evento.text = intent.getStringExtra("calendar")
        from_hora.text =intent.getStringExtra("from")
        to_hora.text =intent.getStringExtra("to")
    }



    private fun ChangeState(id:String,state:String){
        val dbRef = FirebaseDatabase.getInstance().getReference("new_event").child(id)
        dbRef.child("state").setValue(state)
        Toast.makeText(applicationContext,"Event marked as finished",Toast.LENGTH_LONG).show()


    }

    private fun openUpdateDialog(
        evId : String,
        evName:String
    ) {

        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.modify_event, null)


        //-----------------------------------------calendario


        mDialog.setView(mDialogView)
        val etEventName = mDialogView.findViewById<EditText>(R.id.nombre)
        val etEventCalendar =
            mDialogView.findViewById<AutoCompleteTextView>(R.id.calendario_dropdown) //en el view de modigy
        from_event_modify = mDialogView.findViewById<EditText>(R.id.from_date_m)
        to_event_modify = mDialogView.findViewById<EditText>(R.id.to_date_m)

        val calendars_names = listOf("Personal", "Work", "Family", "Others")


        //   val etEventReminder = mDialogView.findViewById<EditText>(R.id.reminder)
        val btnSave = mDialogView.findViewById<Button>(R.id.save)

        val btnBack = mDialogView.findViewById<ImageButton>(R.id.back)
        val lunes: ImageButton = mDialogView.findViewById(R.id.monday)
        val martes: ImageButton = mDialogView.findViewById(R.id.tuesday)
        val mierc: ImageButton = mDialogView.findViewById(R.id.wednesday)
        val jueves: ImageButton = mDialogView.findViewById(R.id.thursday)
        val vier: ImageButton = mDialogView.findViewById(R.id.friday)
        val sab: ImageButton = mDialogView.findViewById(R.id.saturday)
        val domin: ImageButton = mDialogView.findViewById(R.id.sunday)



        etEventName.setText(intent.getStringExtra("nombre").toString())
        etEventCalendar.setText(intent.getStringExtra("calendar").toString())
        from_event_modify.setText(intent.getStringExtra("from").toString())
        to_event_modify.setText(intent.getStringExtra("to").toString())


        val alertDialog = mDialog.create()
        alertDialog.show()

        val adapter = ArrayAdapter<String>(this, R.layout.list_item2, calendars_names)
        etEventCalendar.setAdapter(adapter)
        etEventCalendar.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, _, i, _ ->


                val itemSelected = adapterView.getItemAtPosition(i)

            }
        btnBack.setOnClickListener {
            alertDialog.dismiss()
        }

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


        btnSave.setOnClickListener {
            updateEventData(
                evId,
                etEventName.text.toString(),
                etEventCalendar.text.toString(),
                from_event_modify.text.toString(),
                to_event_modify.text.toString(),
                past_calendar


            )







            Toast.makeText(applicationContext,"Event Data Updated",Toast.LENGTH_LONG).show()

            //updatear el text view
            nombre_evento.text =etEventName.text.toString()
            calendario_evento.text = etEventCalendar.text.toString()
            from_hora.text = from_event_modify.text.toString()
            to_hora.text = to_event_modify.text.toString()


            alertDialog.dismiss()
        }

        from_event_modify.setOnClickListener {
            showDatePicker() }
        to_event_modify.setOnClickListener {
            showDatePicker_2() }


    }

    private fun updateEventData(id:String,name:String,calendar:String,from:String,to:String,pastCalendar: String){

        val new_id: String = when (calendar.lowercase()) {
            "personal" -> "-NxoOkxEDldQT5k1baPt"
            "family" -> "-NxoOkxJqjiIWcJPdBng"
            "work" -> "-NxoOkxK8oqtmu3QsUZL"
            else -> "-NxoOkxLgPqacCT5YDQx"}

        val old_id: String = when (pastCalendar.lowercase()) {
            "personal" -> "-NxoOkxEDldQT5k1baPt"
            "family" -> "-NxoOkxJqjiIWcJPdBng"
            "work" -> "-NxoOkxK8oqtmu3QsUZL"
            else -> "-NxoOkxLgPqacCT5YDQx"}


        val dbRef = FirebaseDatabase.getInstance().getReference("new_event").child(id)

        dbRef.child("nombre_ingresado").setValue(name)
        dbRef.child("calendario_ingresado").setValue(calendar)
        dbRef.child("from_hora").setValue(from)
        dbRef.child("to_hora").setValue(to)


        var dbRef_calendars = FirebaseDatabase.getInstance().getReference("calendar")


        dbRef_calendars.child(new_id).child("events").child(id).setValue("true")

        val eventsRef = FirebaseDatabase.getInstance().getReference("calendar").child(old_id).child("events")

        eventsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Iterate over each child node (eventId) within the "events" node
                for (eventSnapshot in snapshot.children) {
                    // Retrieve the key (eventId) and value (eventName) for each child node
                    val eventId = eventSnapshot.key // This will give you the event ID
                    val eventName = eventSnapshot.getValue(String::class.java) // This will give you the event name

                    // Now you can update the value to a new value
                    val newValue = "New Value"
                    eventSnapshot.ref.setValue(newValue) // Update the value to the new value

                    // Print a message indicating that the value has been updated
                    println("Event ID: $eventId, Updated Event Name: $newValue")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value: ${error.message}")
            }
        })








    }

    private fun showDatePicker(){
        val timePicker = TimePickerFragment{fechaSeleccioanda(it)}
        timePicker.show(supportFragmentManager,"timePicker")

    }
    private fun fechaSeleccioanda(time:String) {
        from_hora_m = time
        from_event_modify.setText("$time")
    }
    private fun showDatePicker_2(){
        val timePicker = TimePickerFragment{fechaSeleccioanda_2(it)}
        timePicker.show(supportFragmentManager,"timePicker")

    }
    private fun fechaSeleccioanda_2(time:String) {
        to_hora_m = time
        to_event_modify.setText("$time")


    }

    private fun deleteRecord(id:String){
        val dbRef = FirebaseDatabase.getInstance().getReference("new_event").child(id)
        val mtask = dbRef.removeValue()

        mtask.addOnSuccessListener {
            Toast.makeText(this,"The event information has been deleted",Toast.LENGTH_LONG).show()
            val  intent = Intent(this,MainActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error-> Toast.makeText(this,"Deleting Err ${error.message}",Toast.LENGTH_LONG).show()}


    }




}