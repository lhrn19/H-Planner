package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prueba2.Category
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewCategory_popup_activity : AppCompatActivity() {
    private lateinit var databaseRef : DatabaseReference
    private lateinit var editTextNameCat: EditText
    private lateinit var editTextBudgetCat: EditText
    private lateinit var btnSaveNewCategory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newcategory_popup)
        databaseRef = FirebaseDatabase.getInstance().getReference("New_category")

        editTextNameCat = findViewById(R.id.editTextNameCat)
        editTextBudgetCat = findViewById(R.id.editTextBudgetCat)
        btnSaveNewCategory = findViewById(R.id.btnSaveNewCategory)

        btnSaveNewCategory.setOnClickListener {
            saveNewCategory()
        }

        val go_to_modify_close: ImageButton = findViewById(R.id.closeNewCategory)
        go_to_modify_close.setOnClickListener{
            /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
            val intent_mod_bud = Intent(this, ModifyBudget_activity::class.java)
            startActivity(intent_mod_bud)}
    }
    private fun saveNewCategory() {
        val name = editTextNameCat.text.toString()
        val budget = editTextBudgetCat.text.toString()

        if (name.isEmpty()) {
            editTextNameCat.error = "Please enter Name of Category"
        }
        if (budget.isEmpty()) {
            editTextBudgetCat.error = "Please enter Budget for Category"
        }

        val categoryId = databaseRef.push().key

        if (categoryId != null) {
            val category = Category(categoryId, name, budget)
            databaseRef.child(categoryId).setValue(category)
                .addOnCompleteListener {
                    Toast.makeText(this, "New Category loaded successfully", Toast.LENGTH_LONG)
                        .show()
                    val intent_mod_bud = Intent(this, ModifyBudget_activity::class.java)
                    startActivity(intent_mod_bud)
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error:${err.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}