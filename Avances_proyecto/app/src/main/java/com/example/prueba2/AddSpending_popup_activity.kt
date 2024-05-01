package com.example.prueba2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddSpending_popup_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addspending_popup)

        val go_budget_and_save: Button = findViewById(R.id.saveNewSpending)
        go_budget_and_save.setOnClickListener{
            /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
            val intent_new_spending_save = Intent(this, Budget_main_activity::class.java)
            startActivity(intent_new_spending_save)}

        val go_budget_and_close: ImageButton = findViewById(R.id.closeNewSpending)
        go_budget_and_close.setOnClickListener{
            val intent_new_spending_close = Intent(this, Budget_main_activity::class.java)
            startActivity(intent_new_spending_close)}



    }
}