package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.ImageButton
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.Category
import com.google.firebase.database.FirebaseDatabase

class CategoryBudget_activity : AppCompatActivity() {
    private lateinit var tvId: TextView
    private lateinit var tvName: TextView
    private lateinit var tvBudget: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var titleCategory : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categorybudget)

        initView()
        setValues()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("id").toString(),
                intent.getStringExtra("name").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("id").toString()
            )
        }



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
    private fun deleteRecord(id:String){
        val dbRef = FirebaseDatabase.getInstance().getReference("New_category").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(applicationContext, "Category Data Deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this,ModifyBudget_activity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener {error->
            Toast.makeText(applicationContext, "Deleting error: ${error.message}", Toast.LENGTH_LONG).show()

        }
    }

    private fun initView(){
        tvId = findViewById(R.id.tvEmpId)
        tvName = findViewById(R.id.tvEmpName)
        titleCategory = findViewById(R.id.titleCategory)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

    }
    private fun setValues(){
        tvId.text = intent.getStringExtra("name")
        tvName.text = intent.getStringExtra("budget")
        titleCategory.text = intent.getStringExtra("name")

    }
    private fun openUpdateDialog(catId:String,catName:String){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.modifybudget_popup,null)

        mDialog.setView(mDialogView)

        val newNameCategoryInput = mDialogView.findViewById<EditText>(R.id.newNameCategoryInput)
        val newBudgetCategoryInput = mDialogView.findViewById<EditText>(R.id.newBudgetCategoryInput)

        val saveChangeCategory = mDialogView.findViewById<Button>(R.id.saveChangeCategory)

        newNameCategoryInput.setText(intent.getStringExtra("name")).toString()
        newBudgetCategoryInput.setText(intent.getStringExtra("budget")).toString()

        mDialog.setTitle("Updating $catName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        saveChangeCategory.setOnClickListener{
            updateCatData(catId,
                newNameCategoryInput.text.toString(),
                newBudgetCategoryInput.text.toString())
            Toast.makeText(applicationContext, "Category Data Updated", Toast.LENGTH_LONG).show()
            tvName.text = newNameCategoryInput.text.toString()
            tvBudget.text = newBudgetCategoryInput.text.toString()

            alertDialog.dismiss()
        }

    }
    private fun updateCatData(
        id: String,
        name: String,
        budget: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("New_category").child(id)
        val catInfo = Category(id, name, budget)
        dbRef.setValue(catInfo)
    }
}