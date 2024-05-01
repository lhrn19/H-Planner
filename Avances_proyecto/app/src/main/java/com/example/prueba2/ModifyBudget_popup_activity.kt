package com.example.prueba2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ModifyBudget_popup_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modifybudget_popup)

        val go_to_modify_pop: Button = findViewById(R.id.saveChangeBudgetCategory)
        go_to_modify_pop.setOnClickListener{
            /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
            val intent_mod_bud = Intent(this, CategoryBudget_activity::class.java)
            startActivity(intent_mod_bud)}

        val go_to_cat: ImageButton = findViewById(R.id.closeEditBudgetCat)
        go_to_cat.setOnClickListener{
            /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
            val intent_mod_bud = Intent(this, CategoryBudget_activity::class.java)
            startActivity(intent_mod_bud)}
    }
}