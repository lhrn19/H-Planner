package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba2.Category
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ModifyBudget_activity : AppCompatActivity() {
    private lateinit var categoryRV: RecyclerView
    private lateinit var catList: ArrayList<Category>
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modifybudget)

        categoryRV = findViewById(R.id.rvCat)
        categoryRV.layoutManager = LinearLayoutManager(this)
        categoryRV.setHasFixedSize(true)

        catList = arrayListOf<Category>()

        getCategories()


        val go_new_category: Button = findViewById(R.id.addcategory)
        go_new_category.setOnClickListener{
            /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
            val intent_new_category = Intent(this, NewCategory_popup_activity::class.java)
            startActivity(intent_new_category)}

        val go_to_budget_main: ImageButton = findViewById(R.id.back_to_main)
        go_to_budget_main.setOnClickListener{
            val intent_budget = Intent(this, Budget_main_activity::class.java)
            startActivity(intent_budget)}


        val go_menu_drawer: ImageButton = findViewById(R.id.menu)
        go_menu_drawer.setOnClickListener{
            val intent_menu = Intent(this, Activity_drawer_menu::class.java)
            startActivity(intent_menu)}

    }
    private fun getCategories(){
        categoryRV.visibility = View.GONE
        databaseRef = FirebaseDatabase.getInstance().getReference("New_category")

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                catList.clear()
                if (snapshot.exists()){
                    for (catSnap in snapshot.children){
                        val catData = catSnap.getValue(Category::class.java)
                        catList.add(catData!!)
                    }
                    val mAdapter = CatAdapter(catList)
                    categoryRV.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : CatAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent=Intent(this@ModifyBudget_activity,CategoryBudget_activity::class.java)
                            intent.putExtra("id",catList[position].id)
                            intent.putExtra("name",catList[position].name)
                            intent.putExtra("budget",catList[position].budget)
                            startActivity(intent)
                        }
                    })
                    categoryRV.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}

