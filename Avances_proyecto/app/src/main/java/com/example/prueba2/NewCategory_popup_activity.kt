package com.example.prueba2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewCategory_popup_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newcategory_popup)

        val go_to_modify_close: ImageButton = findViewById(R.id.closeNewCategory)
        go_to_modify_close.setOnClickListener{
            /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
            val intent_mod_bud = Intent(this, ModifyBudget_activity::class.java)
            startActivity(intent_mod_bud)}

        val go_to_modify_save: Button = findViewById(R.id.btnSaveNewCategory)
        go_to_modify_save.setOnClickListener{
            /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
            val intent_mod_bud = Intent(this, ModifyBudget_activity::class.java)
            startActivity(intent_mod_bud)}
    }
}