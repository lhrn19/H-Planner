package com.example.intento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.intento.Usuario
import android.widget.Toast


class NewUserActivity : AppCompatActivity() {
    private lateinit var dbRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_account)

        val editTextUsername: EditText = findViewById(R.id.editText_username_na)
        val editTextNombre: EditText = findViewById(R.id.editText_name_na)
        val editTextEmail: EditText = findViewById(R.id.editText_email_na)
        val editTextGender: EditText = findViewById(R.id.editText_gender_na)
        val editTextTelefone: EditText = findViewById(R.id.editText_telefone_na)
        val editTextBithday: EditText = findViewById(R.id.editText_birthday_na)
        val editText_password_na: EditText = findViewById(R.id.editText_password_na)
        val btnCreate_account: Button = findViewById(R.id.create_account)
        val regresar_new_account: ImageButton = findViewById(R.id.regresar_new_account)

        regresar_new_account.setOnClickListener{
            val intent_account = Intent(this, MainActivity::class.java)
            startActivity(intent_account)}

        var dbRef_nuevousurio = FirebaseDatabase.getInstance().getReference("Usuarios")

        btnCreate_account.setOnClickListener {
            var userId = dbRef_nuevousurio.push().key!!

            if (editTextUsername.text.toString().isEmpty()) {
                editTextUsername.error = "Please enter spending amount"
            }
            if (editTextNombre.text.toString().isEmpty()) {
                editTextNombre.error = "Please enter spending amount"
            }
            if (editTextEmail.text.toString().isEmpty()) {
                editTextEmail.error = "Please enter spending amount"
            }
            if (editTextGender.text.toString().isEmpty()) {
                editTextGender.error = "Please enter spending amount"
            }
            if (editTextTelefone.text.toString().isEmpty()) {
                editTextTelefone.error = "Please enter spending amount"
            }

            if (editText_password_na.text.toString().isEmpty()) {
                editText_password_na.error = "Please enter spending amount"
            } else {


                val usuario = Usuario(
                    editTextNombre.text.toString(),
                    editTextUsername.text.toString(),
                    userId,
                    editTextTelefone.text.toString(),
                    editTextEmail.text.toString(),
                    editTextGender.text.toString(),
                    editTextBithday.text.toString(),
                    editText_password_na.text.toString()
                )
                dbRef_nuevousurio.child(userId).setValue(usuario).addOnCompleteListener {
                    Toast.makeText(
                        this@NewUserActivity,
                        "New Account created successfully",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(
                        this@NewUserActivity,
                        MainActivity::class.java
                    )
                    startActivity(intent)
                }.addOnFailureListener { err ->
                    Toast.makeText(
                        this@NewUserActivity,
                        "Error:${err.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }
    }
}