package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class ProfileInformationActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var userId: String
    private lateinit var btnregreso: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_information)

        userId = intent.getStringExtra("IdUsername") ?: return

        dbRef = FirebaseDatabase.getInstance().getReference("Usuarios").child(userId)

        val textViewUsername: TextView = findViewById(R.id.LinearLayoutUsername)
        val textViewNombre: TextView = findViewById(R.id.LinearLayoutName)
        val textViewEmail: TextView = findViewById(R.id.LinearLayoutEmail)
        val textViewGender: TextView = findViewById(R.id.LinearLayoutGender)
        val textViewTelefone: TextView = findViewById(R.id.LinearLayoutTelefone)
        val textViewBirthday: TextView = findViewById(R.id.LinearLayoutBirthday)


        // Configurar el Button
        btnregreso = findViewById(R.id.regresar_profile_info)
        btnregreso.setOnClickListener {
            val idUsername = intent.getStringExtra("idUsername").toString()
            val intent_toback= Intent(this@ProfileInformationActivity, ProfileInformationActivity::class.java)
            intent_toback.putExtra("idUsername", idUsername)
            startActivity(intent_toback)
        }

    // Cargar los datos del usuario
        dbRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val usuario = snapshot.getValue(Usuario::class.java)
                usuario?.let {
                    textViewUsername.text = it.username
                    textViewNombre.text = it.nombre
                    textViewEmail.text = it.email
                    textViewGender.text = it.gender
                    textViewTelefone.text = it.telefone
                    textViewBirthday.text = it.birthday
                }
            } else {
                Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Error: ${err.message}", Toast.LENGTH_LONG).show()
        }
    }
}

