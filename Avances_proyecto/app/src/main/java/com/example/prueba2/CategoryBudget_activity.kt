package com.example.prueba2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.ImageButton
import android.widget.Button

class CategoryBudget_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categorybudget)

        val go_to_modify_pop: Button = findViewById(R.id.modifybudget)
        go_to_modify_pop.setOnClickListener{
            /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
            val intent_mod_bud = Intent(this, ModifyBudget_popup_activity::class.java)
            startActivity(intent_mod_bud)}

        val go_to_cat: ImageButton = findViewById(R.id.returncategories)
        go_to_cat.setOnClickListener{
            /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
            val intent_mod_bud = Intent(this, ModifyBudget_activity::class.java)
            startActivity(intent_mod_bud)}


        val go_menu_drawer: ImageButton = findViewById(R.id.menu)
        go_menu_drawer.setOnClickListener{
            val intent_menu = Intent(this, Activity_drawer_menu::class.java)
            startActivity(intent_menu)}
    }
}