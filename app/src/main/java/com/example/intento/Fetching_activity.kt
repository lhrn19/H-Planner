
package com.example.intento
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//para el fetching de lso eventos


class Fetching_activity : AppCompatActivity() {

    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var eventList: ArrayList<Evento>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        eventRecyclerView =  findViewById(R.id.rvEvento)
        eventRecyclerView.layoutManager = LinearLayoutManager(this)
        eventRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        eventList = arrayListOf<Evento>()

        getEventsData()


        }
    private  fun getEventsData(){
        eventRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE
        dbRef = FirebaseDatabase.getInstance().getReference("new_event")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()
                if(snapshot.exists()){
                    for (eventSnap in snapshot.children){
                        val eventData = eventSnap.getValue(Evento::class.java)
                        eventList.add(eventData!!)
                    }
                    val mAdapter = EventAdapter(eventList)
                    eventRecyclerView.adapter = mAdapter

                    eventRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



    }
    }









