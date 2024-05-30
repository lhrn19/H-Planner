package com.example.prueba2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {

    private lateinit var btnPrueba: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        btnPrueba = findViewById(R.id.prueba)
        btnPrueba.setOnClickListener {
            val idUsername = intent.getStringExtra("idUsername").toString()
            val intent_toaccount= Intent(this@DashboardActivity,ConsultarUserActivity::class.java)
            intent.putExtra("idUsername", idUsername)
            val intent = Intent(this, ProfileInformationActivity::class.java)
            intent.putExtra("idUsername", idUsername)
            startActivity(intent)

            startActivity(intent_toaccount)
            }
    }
}