package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var databaseRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.budget_main)

        databaseRef = FirebaseDatabase.getInstance().getReference().child("category")


        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        val gonewspending: ImageButton = findViewById(R.id.addnewspending)
        gonewspending.setOnClickListener{
            val intentnewspending = Intent(this, AddSpending_popup_activity::class.java)
            startActivity(intentnewspending)}

        val gotobudgetmain: ImageButton = findViewById(R.id.gocategories)
        gotobudgetmain.setOnClickListener{

            val intentbudget = Intent(this, ModifyBudget_activity::class.java)
            startActivity(intentbudget)
        }

        val gomenudrawer: ImageButton = findViewById(R.id.menu)
        gomenudrawer.setOnClickListener{
            val intentmenu = Intent(this, Activity_drawer_menu::class.java)
            startActivity(intentmenu)}
    }
}