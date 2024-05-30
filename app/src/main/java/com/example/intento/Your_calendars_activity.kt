package com.example.intento

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Your_calendars_activity : AppCompatActivity() {


   private lateinit var colorFamily:ImageButton
    private lateinit var  colorPersonal:ImageButton
    private lateinit var colorWork:ImageButton
    private lateinit var colorOthers:ImageButton
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.your_calendars)
        dbRef = FirebaseDatabase.getInstance().getReference("calendar")

        startCalendars(false)

        colorFamily = findViewById(R.id.color_fam)
        colorPersonal= findViewById(R.id.color_personal)
        colorWork = findViewById(R.id.color_work)
        colorOthers = findViewById(R.id.color_others)


        updateCalendarColors()

        val EditFamily: ImageButton = findViewById(R.id.family)
        val EditWork: ImageButton = findViewById(R.id.work)
        val EditPersonal: ImageButton = findViewById(R.id.personal)
        val EditOthers: ImageButton = findViewById(R.id.others)

        val go_new_habit: Button = findViewById(R.id.go_new_habit)
        go_new_habit.setOnClickListener{
            val intent_new_habit = Intent(this, Add_new_event_activity::class.java)
            startActivity(intent_new_habit)

        }


        EditWork.setOnClickListener{
            openUpdateDialog("Work")
        }

        EditPersonal.setOnClickListener{
            openUpdateDialog("Personal")
        }

        EditOthers.setOnClickListener{
            openUpdateDialog("Others")
        }

        EditFamily.setOnClickListener{
            openUpdateDialog("Family")
        }


        val discard: ImageButton = findViewById(R.id.back)
        discard.setOnClickListener {

            //  Toast.makeText(this, "The new event was not saved", Toast.LENGTH_SHORT).show()
            val intent_discard = Intent(this, Event_main_activity::class.java)
            startActivity(intent_discard)

        }
        val go_menu_drawer: ImageButton = findViewById(R.id.menu)
        go_menu_drawer.setOnClickListener{
            val intent_menu = Intent(this, Activity_drawer_menu::class.java)
            startActivity(intent_menu)}







    }


    private fun updateCalendarColors() {
        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (calendarSnapshot in dataSnapshot.children) {
                    val calendar = calendarSnapshot.getValue(Calendar::class.java)
                    if (calendar != null) {

                        when (calendar.nombre_ingresado) {
                            "Family" -> updateColor(colorFamily, calendar.color.toString(), 200)
                            "Personal" -> updateColor(colorPersonal, calendar.color.toString(), 200)
                            "Work" -> updateColor(colorWork, calendar.color.toString(), 200)
                            "Others" -> updateColor(colorOthers, calendar.color.toString(), 200)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors.
            }
        })
    }

    private fun updateColor(imageView: ImageView, color: String, transparency: Int) {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.OVAL
        val colorcito = when (color) {
            "red" -> R.color.selected_red
            "green" -> R.color.selected_green
            "purple" -> R.color.selected_purple
            "yellow" -> R.color.selected_yellow
            "blue" -> R.color.selected_blue
            else -> R.color.selected_orange
        }
        val parsedColor = ContextCompat.getColor(imageView.context,colorcito)
        val colorWithTransparency = Color.argb(transparency, Color.red(parsedColor), Color.green(parsedColor), Color.blue(parsedColor))

        gradientDrawable.setColor(colorWithTransparency)
        imageView.setImageDrawable(gradientDrawable)
    }

    private fun startCalendars(create:Boolean) {
        if(create){


            val calendarNames = listOf("Personal", "Family", "Work","Others")

            for (name in calendarNames) {
                val calendarId = dbRef.push().key!!
                val calendar = Calendar(calendarId, name, "azul_bello",mapOf("try" to "first"))

                dbRef.child(name).setValue(calendar)
                    .addOnCompleteListener {
                        Toast.makeText(
                            this,
                            "Calendar '$name' inserted successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    .addOnFailureListener { err ->
                        Toast.makeText(
                            this,
                            "Error inserting calendar '$name': ${err.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }

        }
    }



    private fun openUpdateDialog(
        calName:String
    ) {

        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.color_picker, null)


        //-----------------------------------------calendario


        mDialog.setView(mDialogView)
        val RedButton = mDialogView.findViewById<ImageButton>(R.id.red)
        val YellowButton  = mDialogView.findViewById<ImageButton>(R.id.yellow)
        val GreenButton  = mDialogView.findViewById<ImageButton>(R.id.green)
        val OrangeButton = mDialogView.findViewById<ImageButton>(R.id.orange)
        val BlueButton = mDialogView.findViewById<ImageButton>(R.id.blue)
        val PurpleButton = mDialogView.findViewById<ImageButton>(R.id.purple)




        val btnSave = mDialogView.findViewById<Button>(R.id.save)

        val btnBack = mDialogView.findViewById<ImageButton>(R.id.back)
        var color:String = "red"






        val alertDialog = mDialog.create()
        alertDialog.show()


        btnBack.setOnClickListener {
            alertDialog.dismiss()
        }


        RedButton.setOnClickListener {
            color = "red"
        }
        BlueButton.setOnClickListener {
            color = "blue"
        }
        GreenButton.setOnClickListener {
            color = "green"
        }
        PurpleButton.setOnClickListener {
            color = "purple"
        }
        YellowButton.setOnClickListener {
            color = "yellow"
        }
        OrangeButton.setOnClickListener {
            color = "orange"
        }


        btnSave.setOnClickListener {
            updateEventData(color,calName)
           Toast.makeText(applicationContext,"Calendar color Updated",Toast.LENGTH_LONG).show()
            alertDialog.dismiss()
            updateCalendarColors()

        }


    }

    private fun updateEventData(color:String,id:String){



        val dbRef = FirebaseDatabase.getInstance().getReference("calendar").child(id)
        dbRef.child("color").setValue(color)



    }




    }