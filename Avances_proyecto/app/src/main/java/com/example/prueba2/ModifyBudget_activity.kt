package com.example.prueba2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.widget.ImageButton


class ModifyBudget_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modifybudget)

        val go_new_category: Button = findViewById(R.id.addcategory)
        go_new_category.setOnClickListener{
            /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
            val intent_new_category = Intent(this, MainActivity::class.java)
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
}