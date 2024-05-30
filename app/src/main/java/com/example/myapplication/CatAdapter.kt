package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Category
import com.google.api.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CatAdapter (private val catList: ArrayList<Category>):
    RecyclerView.Adapter<CatAdapter.ViewHolder>() {
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_card, parent, false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCat = catList[position]

        val itemView = holder.itemView
        val myTextView = itemView.findViewById<View>(R.id.cardcolor) as LinearLayout
        var dbRef = FirebaseDatabase.getInstance().getReference("New_category")

        val query = dbRef.orderByChild("name").equalTo(currentCat.name)
        println(query)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    val name = childSnapshot.child("name").getValue(String::class.java)
                    println(name)
                    myTextView.setBackgroundResource(
                        when (name.toString()) {
                            "Home" -> R.color.selected_red
                            "Health & Fitness" -> R.color.selected_green
                            "Food & Dinning" -> R.color.selected_purple
                            "Entertainment" -> R.color.selected_blue
                            else -> R.color.selected_orange
                        }
                    )
                }}

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                println("Error querying calendar color: ${error.message}")
            }})

        holder.titlebudget1.text = currentCat.name
        holder.amount.text = currentCat.budget

    }

    override fun getItemCount(): Int {
        return catList.size
    }

    class ViewHolder(itemView: View,clickListener : onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val titlebudget1: TextView = itemView.findViewById(R.id.titlebudget1)
        val amount: TextView = itemView.findViewById(R.id.amount)
        val cardcolor: LinearLayout = itemView.findViewById(R.id.cardcolor)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}