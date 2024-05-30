package com.example.intento

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database

class Sunday_Activity : AppCompatActivity() {
    private lateinit var eventRecyclerView: RecyclerView

    private lateinit var eventList: ArrayList<Evento>
    private lateinit var dbRef: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sun_activity)
        val firebase = com.google.firebase.ktx.Firebase.database.reference
        eventRecyclerView =  findViewById(R.id.rvEvento)
        eventRecyclerView.layoutManager = LinearLayoutManager(this)
        eventRecyclerView.setHasFixedSize(true)
        val back: ImageButton = findViewById(R.id.back)




        eventList = arrayListOf<Evento>()

        getEventsData()


        //------------------------------inicilizar para el fetching
        back.setOnClickListener{
            val intent_discard = Intent(this, Event_main_activity::class.java)
            startActivity(intent_discard)

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

        val prev: ImageView = findViewById(R.id.arrow_prev)
        prev.setOnClickListener{
            val intent_menu = Intent(this, Saturday_Activity::class.java)
            startActivity(intent_menu)}

        val next: ImageView = findViewById(R.id.arrow_next)
        next.setOnClickListener{
            val intent_menu = Intent(this, Monday_Activity::class.java)
            startActivity(intent_menu)}




        // Set a click listener on the TextView

    }

    private  fun getEventsData(){
        eventRecyclerView.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("new_event")

        dbRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()
                if(snapshot.exists()){
                    for (eventSnap in snapshot.children) {
                        val eventData = eventSnap.getValue(Evento::class.java)
                        if(eventData?.domingo_ingresado==true){
                            eventList.add(eventData!!)}

                    }

                    val mAdapter = EventAdapter(eventList)
                    eventRecyclerView.adapter = mAdapter
                    mAdapter.SetOnItemClickListener(object: EventAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@Sunday_Activity,Event_detail_activity::class.java)
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

