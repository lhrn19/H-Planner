package com.example.prueba2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText


class AgregarCategoriaActivity : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance().reference.child("productos")
    private lateinit var editTextNameCat: EditText
    private lateinit var editTextBudgetCat: EditText
    private lateinit var btnSaveNewCategory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newcategory_popup)

        editTextNameCat = findViewById(R.id.editTextNameCat)
        editTextBudgetCat = findViewById(R.id.editTextBudgetCat)

        findViewById<Button>(R.id.btnSaveNewCategory).setOnClickListener {
            val name = editTextNameCat.text.toString()
            val budget = editTextBudgetCat.text

            // Crear un nuevo producto en Firebase
            val categoryId = database.push().key
            if (categoryId != null) {
                val category = Category(categoryId, name, budget)
                database.child(categoryId).setValue(category)
            }

            finish() // Finalizar la actividad después de agregar el producto
        }
    }
}