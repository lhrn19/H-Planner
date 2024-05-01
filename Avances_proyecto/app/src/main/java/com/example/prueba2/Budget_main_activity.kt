package com.example.prueba2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class Budget_main_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.budget_main)

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
}