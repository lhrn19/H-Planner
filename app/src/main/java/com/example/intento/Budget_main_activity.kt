package com.example.intento

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.intento.databinding.BudgetMainBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet
import com.github.mikephil.charting.utils.MPPointF
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Budget_main_activity : AppCompatActivity() {
    private lateinit var spendingList: ArrayList<Spending>
    private lateinit var databaseRef: DatabaseReference
    private lateinit var binding: BudgetMainBinding
    val spendingValues = ArrayList<PieEntry>()
    private lateinit var amount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BudgetMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spendingList = arrayListOf<Spending>()
        amount = findViewById(R.id.amount)

        getSpendings(object : SpendingsCallback {
            override fun onSpendingsLoaded(amounts: List<Int>, categories: List<String>) {
                for (i in amounts.indices) {
                    val formattedLabel = "${categories[i]}\n: ${amounts[i]}"
                    spendingValues.add(PieEntry(amounts[i].toFloat(), formattedLabel))
                }
                setChart()

            }
        })

        val go_new_spending: ImageButton = findViewById(R.id.addnewspending)
        go_new_spending.setOnClickListener{
            /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
            val intent_new_spending = Intent(this, AddSpending_popup_activity::class.java)
            startActivity(intent_new_spending)}

        val go_to_budget_main: ImageButton = findViewById(R.id.gocategories)
        go_to_budget_main.setOnClickListener{
            val intent_budget = Intent(this, ModifyBudget_activity::class.java)
            startActivity(intent_budget)}

        val go_menu_drawer: ImageButton = findViewById(R.id.menu)
        go_menu_drawer.setOnClickListener{
            val intent_menu = Intent(this, Activity_drawer_menu::class.java)
            startActivity(intent_menu)}
    }
    private fun getSpendings(callback: SpendingsCallback){
        databaseRef = FirebaseDatabase.getInstance().getReference("New_Spending")

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                spendingList.clear()
                if (snapshot.exists()){
                    for (catSnap in snapshot.children){
                        val catData = catSnap.getValue(Spending::class.java)
                        spendingList.add(catData!!)
                    }

                    val arrayList = ArrayList<Int>()
                    val catList = mutableListOf<String>()
                    for (spending in spendingList) {
                        arrayList.add(spending.amount?.toIntOrNull() ?: 0)
                        catList.add(spending.category ?: "Unknown")
                    }

                    val totalSpending = spendingList.sumBy { it.amount?.toIntOrNull() ?: 0 }

                    println(arrayList)
                    println(catList)
                    println(totalSpending)

                    amount.text = totalSpending.toString()



                    callback.onSpendingsLoaded(arrayList, catList)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun setChart(){
        val pieDataSettter: PieDataSet
        if (binding.pieChart.data != null && binding.pieChart.data.dataSetCount >0){
            pieDataSettter = binding.pieChart.data.getDataSetByIndex(0) as PieDataSet
            pieDataSettter.values = spendingValues
            binding.pieChart.data.notifyDataChanged()
            binding.pieChart.notifyDataSetChanged()
        }else{
            pieDataSettter = PieDataSet(spendingValues,"")

            val blue1 = ContextCompat.getColor(this, R.color.selected_red)
            val blue2 = ContextCompat.getColor(this, R.color.selected_green)
            val blue3 = ContextCompat.getColor(this, R.color.selected_purple)
            val blue4 = ContextCompat.getColor(this, R.color.selected_blue)
            val blue5 = ContextCompat.getColor(this, R.color.selected_orange)

            // Set custom colors to PieDataSet
            pieDataSettter.colors = listOf(blue1, blue2,blue3,blue5,blue4)

            pieDataSettter.setDrawValues(false)
            pieDataSettter.sliceSpace =5f
            pieDataSettter.iconsOffset = MPPointF(10f,10f)
            pieDataSettter.selectionShift = 10f

            val dataSet = ArrayList<IPieDataSet>()
            dataSet.add(pieDataSettter)

            val data = PieData(pieDataSettter)
            binding.pieChart.data = data
            binding.pieChart.invalidate()
            binding.pieChart.description.isEnabled=false
            binding.pieChart.holeRadius = 30f
            binding.pieChart.transparentCircleRadius = 35f

            val typeface = ResourcesCompat.getFont(this, R.font.montserrat_medium)
            val boldTypeface = Typeface.create(typeface, Typeface.BOLD)
            binding.pieChart.setEntryLabelTypeface(boldTypeface)

            binding.pieChart.legend.isEnabled = false


            binding.pieChart.setExtraOffsets(0f, 0f, 0f, 10f)


        }
    }
    interface SpendingsCallback {
        fun onSpendingsLoaded(amounts: List<Int>, categories: List<String>)
    }
}