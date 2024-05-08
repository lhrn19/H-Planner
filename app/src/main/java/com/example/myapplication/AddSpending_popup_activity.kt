package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prueba2.Category
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore

class AddSpending_popup_activity : AppCompatActivity() {
    private lateinit var databaseRef : Firebase
    private lateinit var amountSpending: EditText
    private lateinit var editTextBudgetCat: EditText
    private lateinit var saveNewSpending: Button
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addspending_popup)

        /*databaseRef = Firebase.firestore.collection("Spending").get()*/

        amountSpending = findViewById(R.id.amountSpending)
        saveNewSpending = findViewById(R.id.saveNewSpending)

        saveNewSpending.setOnClickListener {
            saveNewS()
        }

        val gobudgetandsave: Button = findViewById(R.id.saveNewSpending)
        gobudgetandsave.setOnClickListener{
            /*Toast.makeText(this,"New event was correctly added", Toast.LENGTH_SHORT).show()*/
            val intentnewspendingsave = Intent(this, Budget_main_activity::class.java)
            startActivity(intentnewspendingsave)}

        val gobudgetandclose: ImageButton = findViewById(R.id.closeNewSpending)
        gobudgetandclose.setOnClickListener{
            val intentnewspendingclose = Intent(this, Budget_main_activity::class.java)
            startActivity(intentnewspendingclose)}



    }
    private fun saveNewS(){
        val amount = amountSpending.text.toString()
        val budget = editTextBudgetCat.text.toString()

        if (amount.isEmpty()) {
            amountSpending.error = "Please enter Name of Category"
        }
        if (budget.isEmpty()) {
            editTextBudgetCat.error = "Please enter Budget for Category"
        }
        /*
        val spendingId = databaseRef.add().key

        if (spendingId != null) {
            val category = Category(spendingId, amount, budget)
            databaseRef.add(category)
                .addOnCompleteListener {
                    Toast.makeText(this, "New Category loaded successfully", Toast.LENGTH_LONG)
                        .show()
                    val intent_mod_bud = Intent(this, ModifyBudget_activity::class.java)
                    startActivity(intent_mod_bud)
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error:${err.message}", Toast.LENGTH_LONG).show()
                }
        }*/
    }
}