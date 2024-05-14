package com.example.intento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Your_calendars_activity : AppCompatActivity() {


    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.your_calendars)
        dbRef = FirebaseDatabase.getInstance().getReference("calendar")

        startCalendars(false)

        val go_new_habit: Button = findViewById(R.id.go_new_habit)
        go_new_habit.setOnClickListener{
            val intent_new_habit = Intent(this, Add_new_event_activity::class.java)
            startActivity(intent_new_habit)

        }

        val discard_new_event: ImageButton = findViewById(R.id.back)
        discard_new_event.setOnClickListener {

            //  Toast.makeText(this, "The new event was not saved", Toast.LENGTH_SHORT).show()
            val intent_discard = Intent(this, MainActivity::class.java)
            startActivity(intent_discard)

        }






    }

    private fun startCalendars(create:Boolean) {
        if(create){


            val calendarNames = listOf("Personal", "Family", "Work","Others")

            for (name in calendarNames) {
                val calendarId = dbRef.push().key!!
                val calendar = Calendar(calendarId, name, "azul_bello",mapOf("try" to "first"))

                dbRef.child(calendarId).setValue(calendar)
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
    }