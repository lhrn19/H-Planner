package com.example.intento

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ModificarUserActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var dbRefCurrentUser: DatabaseReference
    private lateinit var userId: String

    private lateinit var editTextUsername: EditText
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextGender: EditText
    private lateinit var editTextTelefone: EditText
    private lateinit var editTextBirthday: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonUpdate: Button
    private lateinit var regresar_update_profile_info:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_information)

        editTextUsername = findViewById(R.id.editText_username_upi)
        editTextName = findViewById(R.id.editText_name_upi)
        editTextEmail = findViewById(R.id.editText_email_upi)
        editTextGender = findViewById(R.id.editText_gender_upi)
        editTextTelefone = findViewById(R.id.editText_telefone_upi)
        editTextBirthday = findViewById(R.id.editText_birthday_upi)
        editTextPassword = findViewById(R.id.editText_password_upi)
        regresar_update_profile_info = findViewById(R.id.regresar_update_profile_info)
        buttonUpdate = findViewById(R.id.update)

        dbRefCurrentUser = FirebaseDatabase.getInstance().getReference("current_user_id")

        regresar_update_profile_info.setOnClickListener{
            val intent_account = Intent(this, ConsultarUserActivity::class.java)
            startActivity(intent_account)}

        // Fetch the current user ID
        dbRefCurrentUser.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val userId= snapshot.getValue(String::class.java) ?: ""
                print(userId)

                if (userId.isNotEmpty()) {
                    dbRef = FirebaseDatabase.getInstance().getReference("Usuarios").child(userId)
                    println(dbRef)

                    // Load the current user data
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
                                editTextPassword.setText(it.password)
                            }
                        } else {
                            Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
                        }
                    }.addOnFailureListener { err ->
                        Toast.makeText(this, "Error: ${err.message}", Toast.LENGTH_LONG).show()
                    }
                    buttonUpdate.setOnClickListener {
                        saveUserChanges(userId)
                        val intent_account = Intent(this, ConsultarUserActivity::class.java)
                        startActivity(intent_account)
                    }
                } else {
                    Toast.makeText(this, "User ID not found", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Current user data not found", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Error: ${err.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveUserChanges(id:String) {
        val username = editTextUsername.text.toString()
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val gender = editTextGender.text.toString()
        val telefone = editTextTelefone.text.toString()
        val birthday = editTextBirthday.text.toString()
        val password = editTextPassword.text.toString()
        val dbRef = FirebaseDatabase.getInstance().getReference("Usuarios").child(id)
        println(dbRef)
        println(username)
        println(name)
        println(birthday)

        val user = Usuario(
            idUsername = id,
            username = username,
            nombre = name,
            email = email,
            gender = gender,
            telefone = telefone,
            birthday = birthday,
            password = password
        )
        dbRef.setValue(user)
    }
}
