package com.example.intento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase






class Event_main_activity : AppCompatActivity() {
    private lateinit var eventRecyclerView: RecyclerView

    private lateinit var eventList: ArrayList<Evento>
    private lateinit var dbRef: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        val firebase = Firebase.database.reference
        eventRecyclerView =  findViewById(R.id.rvEvento)
        eventRecyclerView.layoutManager = LinearLayoutManager(this)
        eventRecyclerView.setHasFixedSize(true)


        eventList = arrayListOf<Evento>()

        getEventsData()


 //------------------------------inicilizar para el fetching



        val go_new_habit: Button = findViewById(R.id.go_new_habit)
        go_new_habit.setOnClickListener{
            val intent_new_habit = Intent(this, Add_new_event_activity::class.java)
            startActivity(intent_new_habit)

        }

        val go_your_calendars: Button = findViewById(R.id.show_calendars)
        go_your_calendars.setOnClickListener{
            val intent_calendar = Intent(this, Your_calendars_activity::class.java)
            startActivity(intent_calendar)
        }
        val go_menu_drawer: ImageButton = findViewById(R.id.menu)
        go_menu_drawer.setOnClickListener{
            val intent_menu = Intent(this, Activity_drawer_menu::class.java)
            startActivity(intent_menu)}

//--------------------------------PARA LOS DIAS
        val mondayDailyView: LinearLayout = findViewById(R.id.monday_button)

        // Set a click listener on the TextView
        mondayDailyView.setOnClickListener {
            val intent_upcoming = Intent(this, Monday_Activity::class.java)
            startActivity(intent_upcoming)
        }


        val tuesdayDailyView: LinearLayout = findViewById(R.id.tuesday_button)

        // Set a click listener on the TextView
        tuesdayDailyView.setOnClickListener {
            val intent_upcoming = Intent(this, Tuesday_Activity::class.java)
            startActivity(intent_upcoming)
        }

        val wednesdayDailyView: LinearLayout = findViewById(R.id.wednesday_button)

        // Set a click listener on the TextView
        wednesdayDailyView.setOnClickListener {
            val intent_upcoming = Intent(this, Wednesday_Activity::class.java)
            startActivity(intent_upcoming)
        }

        val thursdayDailyView: LinearLayout = findViewById(R.id.thursday_button)

        // Set a click listener on the TextView
        thursdayDailyView.setOnClickListener {
            val intent_upcoming = Intent(this, Thursday_Activity::class.java)
            startActivity(intent_upcoming)
        }


        val fridayDailyView: LinearLayout = findViewById(R.id.friday_button)

        // Set a click listener on the TextView
        fridayDailyView.setOnClickListener {
            val intent_upcoming = Intent(this, Friday_Activity::class.java)
            startActivity(intent_upcoming)
        }

        val saturdayDailyView: LinearLayout = findViewById(R.id.saturday_button)

        // Set a click listener on the TextView
        saturdayDailyView.setOnClickListener {
            val intent_upcoming = Intent(this, Saturday_Activity::class.java)
            startActivity(intent_upcoming)
        }

        val sundayDailyView: LinearLayout = findViewById(R.id.sunday_button)

        // Set a click listener on the TextView
        sundayDailyView.setOnClickListener {
            val intent_upcoming = Intent(this, Sunday_Activity::class.java)
            startActivity(intent_upcoming)
        }


    }

    private  fun getEventsData(){
        eventRecyclerView.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("new_event")

        dbRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()
                if(snapshot.exists()){
                    for (eventSnap in snapshot.children){
                        val eventData = eventSnap.getValue(Evento::class.java)
                        eventList.add(eventData!!)
                    }
                    val mAdapter = EventAdapter(eventList)
                    eventRecyclerView.adapter = mAdapter
                    mAdapter.SetOnItemClickListener(object: EventAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@Event_main_activity,Event_detail_activity::class.java)
                            intent.putExtra("Id",eventList[position].Id)
                            intent.putExtra("nombre",eventList[position].nombre_ingresado)
                            intent.putExtra("calendar",eventList[position].calendario_ingresado)
                            intent.putExtra("from",eventList[position].from_hora)
                            intent.putExtra("to",eventList[position].to_hora)
                            startActivity(intent)
                        }

                    })


                    eventRecyclerView.visibility = View.VISIBLE




                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



    }
}

