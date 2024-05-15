package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.firestore

class AddSpending_popup_activity : AppCompatActivity() {
    private lateinit var amountSpending: EditText
    private lateinit var saveNewSpending: Button
    private lateinit var autoCompleteTextView: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addspending_popup)

        amountSpending = findViewById(R.id.amountSpending)
        saveNewSpending = findViewById(R.id.saveNewSpending)
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView)


        val categoryNames = listOf("Home","Fitness","Food")
        val adapter = ArrayAdapter<String>(this, R.layout.list_item, categoryNames)
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, i, _ ->
            val itemSelected = adapterView.getItemAtPosition(i)
        }

        saveNewSpending.setOnClickListener {
            saveNewS()
        }

        val gobudgetandclose: ImageButton = findViewById(R.id.closeNewSpending)
        gobudgetandclose.setOnClickListener{
            val intentnewspendingclose = Intent(this, Budget_main_activity::class.java)
            startActivity(intentnewspendingclose)}
    }

    private fun saveNewS(){
        val amount = amountSpending.text.toString()
        val category = autoCompleteTextView.text.toString()
        val dbRef = FirebaseDatabase.getInstance().getReference("New_Spending")

        if (amount.isEmpty()) {
            amountSpending.error = "Please enter spending amount"
        }
        if (category.isEmpty()) {
            autoCompleteTextView.error = "Please enter Name of Category"
        }
        val spendingId = dbRef.push().key

        if (spendingId != null) {
            val spending = Spending(spendingId, amount, category)
            dbRef.child(spendingId).setValue(spending)
                .addOnCompleteListener {
                    Toast.makeText(this, "New Spending loaded successfully", Toast.LENGTH_LONG)
                        .show()
                    val intent_mod_bud = Intent(this, Budget_main_activity::class.java)
                    startActivity(intent_mod_bud)
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error:${err.message}", Toast.LENGTH_LONG).show()
                }
        }

    }
}
