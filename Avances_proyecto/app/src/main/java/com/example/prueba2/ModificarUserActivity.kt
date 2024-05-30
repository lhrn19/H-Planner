package com.example.prueba2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ModificarUserActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var userId: String

    private lateinit var editTextUsername: EditText
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextGender: EditText
    private lateinit var editTextTelefone: EditText
    private lateinit var editTextBirthday: EditText
    private lateinit var buttonUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_information)

        editTextUsername = findViewById(R.id.editText_username_upi)
        editTextName = findViewById(R.id.editText_name_upi)
        editTextEmail = findViewById(R.id.editText_email_upi)
        editTextGender = findViewById(R.id.editText_gender_upi)
        editTextTelefone = findViewById(R.id.editText_telefone_upi)
        editTextBirthday = findViewById(R.id.editText_birthday_upi)
        buttonUpdate = findViewById(R.id.update)

        userId = intent.getStringExtra("idUsername") ?: return
        dbRef = FirebaseDatabase.getInstance().getReference("Usuarios").child(userId)

        // Cargar los datos actuales del usuario
        dbRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val usuario = snapshot.getValue(Usuario::class.java)
                usuario?.let {
                    editTextUsername.setText(it.username)
                    editTextName.setText(it.nombre)
                    editTextEmail.setText(it.email)
                    editTextGender.setText(it.gender)
                    editTextTelefone.setText(it.telefone)
                    editTextBirthday.setText(it.birthday)
                }
            } else {
                Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Error: ${err.message}", Toast.LENGTH_LONG).show()
        }

        buttonUpdate.setOnClickListener {
            saveUserChanges()
        }
    }

    private fun saveUserChanges() {
        val username = editTextUsername.text.toString()
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val gender = editTextGender.text.toString()
        val telefone = editTextTelefone.text.toString()
        val birthday = editTextBirthday.text.toString()

        if (username.isEmpty() || name.isEmpty() || email.isEmpty() || gender.isEmpty() || telefone.isEmpty() || birthday.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show()
            return
        }

        val user = Usuario(
            idUsername = userId,
            username = username,
            nombre = name,
            email = email,
            gender = gender,
            telefone = telefone,
            birthday = birthday
        )

        dbRef.setValue(user).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "User updated successfully", Toast.LENGTH_LONG).show()
                val intent = Intent(this, ProfileInformationActivity::class.java)
                intent.putExtra("idUsername", userId)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Failed to update user", Toast.LENGTH_LONG).show()
            }
        }
    }
}