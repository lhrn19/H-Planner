package com.example.prueba2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseError

class ConsultarUserActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference

    private lateinit var LinearLayoutUsername: TextView
    private lateinit var LinearLayoutName: TextView
    private lateinit var LinearLayoutEmail: TextView
    private lateinit var LinearLayoutGender: TextView
    private lateinit var LinearLayoutTelefone: TextView
    private lateinit var LinearLayoutBirthday: TextView
    private lateinit var currentUserId: String
    private lateinit var btnLogOut: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_information)

        LinearLayoutUsername = findViewById(R.id.LinearLayoutUsername)
        LinearLayoutName = findViewById(R.id.LinearLayoutName)
        LinearLayoutEmail = findViewById(R.id.LinearLayoutEmail)
        LinearLayoutGender = findViewById(R.id.LinearLayoutGender)
        LinearLayoutTelefone = findViewById(R.id.LinearLayoutTelefone)
        LinearLayoutBirthday = findViewById(R.id.LinearLayoutBirthday)

        val idUsername = intent.getStringExtra("idUsername").toString()

        btnLogOut = findViewById(R.id.sign_out) //logout
        btnLogOut.setOnClickListener {
            user_sign_out()
        }

        //val regresar_profile_info: ImageButton = findViewById(R.id.regresar_profile_info)
        //regresar_profile_info.setOnClickListener {
        //    val intent_mod_bud = Intent(this, Activity_drawer_menu::class.java)
        //    startActivity(intent_mod_bud)
        //}

        val edit_profile_information: ImageButton = findViewById(R.id.edit_profile_information)
        edit_profile_information.setOnClickListener {
            setContentView(R.layout.edit_profile_information)
        }
        val text_change_password: Button = findViewById(R.id.change_password) //boton para cambiar contraseña
        text_change_password.setOnClickListener {
            val intent_change_pass = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent_change_pass)
        }

        val intent = Intent(this, ModificarUserActivity::class.java) //intent para modificar****
        intent.putExtra("idUsername", currentUserId)
        startActivity(intent)


        // Retrieve the current user ID from the database
        val dbRefCurrentUser = FirebaseDatabase.getInstance().getReference("current_user_id")
        dbRefCurrentUser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    currentUserId = dataSnapshot.getValue(String::class.java) ?: ""
                    fetchUserDetails(currentUserId) //llamo la función para poder poner los valores de ese usuario
                } else {
                    Toast.makeText(this@ConsultarUserActivity, "No current user ID found.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ConsultarUserActivity, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchUserDetails(idUsername: String) {
        val dbRef_nuevousuario = FirebaseDatabase.getInstance().getReference("Usuarios")
        dbRef_nuevousuario.child(idUsername).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val nombre = dataSnapshot.child("nombre").getValue(String::class.java) ?: ""
                    val username = dataSnapshot.child("username").getValue(String::class.java) ?: ""
                    val telefone = dataSnapshot.child("telefone").getValue(String::class.java) ?: ""
                    val email = dataSnapshot.child("email").getValue(String::class.java) ?: ""
                    val gender = dataSnapshot.child("gender").getValue(String::class.java) ?: ""
                    val birthday = dataSnapshot.child("birthday").getValue(String::class.java) ?: ""

                    LinearLayoutUsername.text = username
                    LinearLayoutName.text = nombre
                    LinearLayoutEmail.text = email
                    LinearLayoutTelefone.text = telefone
                    LinearLayoutBirthday.text = birthday
                    LinearLayoutGender.text = gender
                } else {
                    Toast.makeText(this@ConsultarUserActivity, "User ID not found.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ConsultarUserActivity, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun user_sign_out() {
        FirebaseAuth.getInstance().signOut()
        // Redirigir al usuario a la pantalla de inicio de sesión
        val intent = Intent(this@ConsultarUserActivity, MainActivity::class.java)
        // Limpiar la pila de actividades
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
