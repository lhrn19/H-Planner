package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter
import com.example.myapplication.pieChart
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.myapplication.databinding.ActivityPieChartBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF

class pieChart : AppCompatActivity() {
    private lateinit var spendingList: ArrayList<Spending>
    private lateinit var databaseRef: DatabaseReference
    private lateinit var binding: ActivityPieChartBinding
    val spendingValues = ArrayList<PieEntry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPieChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spendingList = arrayListOf<Spending>()

        println(spendingList)

        getSpendings(object : SpendingsCallback {
            override fun onSpendingsLoaded(amounts: List<Int>, categories: List<String>) {
                for (i in amounts.indices) {
                    //
                    println(amounts[i])
                    println(categories[i])
                    val formattedLabel = "${categories[i]}\n: ${amounts[i]}"
                    spendingValues.add(PieEntry(amounts[i].toFloat(), formattedLabel))
                }
                setChart()
            }
        })


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

                    println(arrayList)
                    println(catList)
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

            val blue1 = ContextCompat.getColor(this, R.color.Blue1)
            val blue2 = ContextCompat.getColor(this, R.color.Blue2)
            val blue3 = ContextCompat.getColor(this, R.color.Blue3)
            val blue4 = ContextCompat.getColor(this, R.color.Blue4)
            val blue5 = ContextCompat.getColor(this, R.color.blue)

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

            val legend = binding.pieChart.legend
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.orientation = Legend.LegendOrientation.HORIZONTAL
            legend.xOffset = 0f
            legend.yOffset = 0f
            legend.textSize = 15f
            legend.formSize = 16f
            legend.xEntrySpace = 40f
            legend.yEntrySpace = 50f


            binding.pieChart.setExtraOffsets(0f, 0f, 0f, 10f)


        }
    }
    interface SpendingsCallback {
        fun onSpendingsLoaded(amounts: List<Int>, categories: List<String>)
    }

}