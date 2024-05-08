package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba2.Category

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
        holder.titlebudget1.text = currentCat.name
        holder.amount.text = currentCat.budget
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    class ViewHolder(itemView: View,clickListener : onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val titlebudget1: TextView = itemView.findViewById(R.id.titlebudget1)
        val amount: TextView = itemView.findViewById(R.id.amount)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}