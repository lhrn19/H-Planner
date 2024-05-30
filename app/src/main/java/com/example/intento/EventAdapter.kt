package com.example.intento

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class EventAdapter(private val eventlist:ArrayList<Evento>):
    RecyclerView.Adapter<EventAdapter.ViewHolder>(){

     private lateinit var mListener: onItemClickListener
    private lateinit var  linearLayout: CardView

    interface onItemClickListener{
         fun onItemClick(position: Int)
     }
    fun SetOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.emp_list_item, parent, false)
        return ViewHolder(itemView, mListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEvent = eventlist[position]



       // holder.linearLayout.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, backgroundTint))
        val itemView = holder.itemView
        val myTextView = itemView.findViewById<View>(R.id.ola2) as LinearLayout
        var dbRef_calendars = FirebaseDatabase.getInstance().getReference("calendar")


        val query = dbRef_calendars.orderByChild("nombre_ingresado").equalTo(currentEvent.calendario_ingresado)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    val color = childSnapshot.child("color").getValue(String::class.java)
                    myTextView.setBackgroundResource(
                        when (color) {
                            "red" -> R.color.selected_red
                            "green" -> R.color.selected_green
                            "purple" -> R.color.selected_purple
                            "yellow" -> R.color.selected_yellow
                            "blue" -> R.color.selected_blue
                            else -> R.color.selected_orange
                        }
                    )
                }}

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                println("Error querying calendar color: ${error.message}")
            }})

        //color_sel = dbRef.child(aux_id)




        holder.tvEventName.text= currentEvent.nombre_ingresado
        holder.tvEvent_from.text= currentEvent.from_hora

    }



    override fun getItemCount(): Int {
       return eventlist.size
    }

    class ViewHolder(itemView:View,clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        val tvEventName: TextView = itemView.findViewById(R.id.tvEventName)
        val tvEvent_from: TextView = itemView.findViewById(R.id.tvEmpFrom)


        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }



    }

}