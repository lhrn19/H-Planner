package com.example.intento

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.Category
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NewCategory_popup_activity : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var editTextBudgetCat: EditText
    private lateinit var btnSaveNewCategory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newcategory_popup)
        dbRef = FirebaseDatabase.getInstance().getReference("New_category")

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView)
        editTextBudgetCat = findViewById(R.id.editTextBudgetCat)
        btnSaveNewCategory = findViewById(R.id.btnSaveNewCategory)

        val categoryNames = listOf("Home","Health & Fitness","Food & Dinning","Entertainment","Others")
        val adapter = ArrayAdapter<String>(this, R.layout.list_item, categoryNames)
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, i, _ ->
            val itemSelected = adapterView.getItemAtPosition(i)
        }

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
        val name = autoCompleteTextView.text.toString()
        val budget = editTextBudgetCat.text.toString()

        if (name.isEmpty()) {
            autoCompleteTextView.error = "Please enter Name of Category"
        }
        if (budget.isEmpty()) {
            editTextBudgetCat.error = "Please enter Budget for Category"
        }

        val spendingQuery = dbRef.orderByChild("name").equalTo(name)
        println(spendingQuery)// Check for existing categories
        spendingQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.childrenCount.toInt() == 0) {  // Category doesn't exist, add it to spending
                    val spendingId = dbRef.push().key  // Generate unique ID for spending
                    if (spendingId != null) {
                        val spending = Category(spendingId, name, budget)
                        dbRef.child(spendingId).setValue(spending)
                            .addOnCompleteListener {
                                Toast.makeText(
                                    this@NewCategory_popup_activity,
                                    "New Spending loaded successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                val intent_mod_bud = Intent(
                                    this@NewCategory_popup_activity,
                                    ModifyBudget_activity::class.java
                                )
                                startActivity(intent_mod_bud)
                            }.addOnFailureListener { err ->
                                Toast.makeText(
                                    this@NewCategory_popup_activity,
                                    "Error:${err.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                    }
                } else {
                    val existingSpending = snapshot.children.first()  // Get first spending with matching category
                    val existingAmount = existingSpending.child("budget").value.toString()
                    existingSpending.ref.updateChildren(hashMapOf("amount" to existingAmount) as Map<String, String>)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this@NewCategory_popup_activity,
                                "Update on edit",
                                Toast.LENGTH_LONG).show()
                            val intent_mod_bud = Intent(
                                this@NewCategory_popup_activity,
                                ModifyBudget_activity::class.java
                            )
                            startActivity(intent_mod_bud)
                        }
                        .addOnFailureListener { err ->
                            Toast.makeText(
                                this@NewCategory_popup_activity,
                                "Error updating spending: ${err.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle database errors
            }
        })
    }

        /*val categoryId = databaseRef.push().key

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
        }*/

}