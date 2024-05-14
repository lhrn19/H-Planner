package com.example.intento

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class EventAdapter(private val eventlist:ArrayList<Evento>):
    RecyclerView.Adapter<EventAdapter.ViewHolder>(){

     private lateinit var mListener: onItemClickListener

     interface onItemClickListener{
         fun onItemClick(position: Int)
     }
    fun SetOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.emp_list_item,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEvent = eventlist[position]
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