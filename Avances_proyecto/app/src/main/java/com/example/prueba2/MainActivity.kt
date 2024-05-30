package com.example.prueba2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var autoCompleteTextView: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //create profile,change password,user_info
        setContentView(R.layout.login)

        editTextPassword = findViewById(R.id.editText_password_lg)
        editTextUsername = findViewById(R.id.editText_username_lg)

        val boton_sign_up = findViewById<Button>(R.id.sign_up)
        boton_sign_up.setOnClickListener {
            val intent_user = Intent(this, NewUserActivity::class.java)
            startActivity(intent_user)
        }
        val boton_sign_in = findViewById<Button>(R.id.sign_in)
        boton_sign_in.setOnClickListener {
            user_sign_in()
        }
    }

    private fun user_sign_in() {
        var dbRef_nuevousuario = FirebaseDatabase.getInstance().getReference("Usuarios")
        val username = editTextUsername.text.toString()
        val password = editTextPassword.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            editTextUsername.error = "Please enter username"
            editTextPassword.error = "Please enter password"
        } else {

            val accountQuery = dbRef_nuevousuario.orderByChild("username").equalTo(username)

            accountQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Remove loading indicator or enable button if used

                    if (dataSnapshot.exists()) {
                        for (data in dataSnapshot.children) {
                            val user = data.getValue(Usuario::class.java)
                            if (user != null) {
                                val intent =
                                    Intent(this@MainActivity, DashboardActivity::class.java)
                                intent.putExtra("username", username)
                                intent.putExtra("idUsername", user.idUsername)
                                startActivity(intent)
                                finish() //añadi esto ******
                                break; // Exit the loop after finding the user
                            }
                        }
                    } else {
                        // Username not found, prompt user to create an account
                        Toast.makeText(
                            this@MainActivity,
                            "Username not found. Please create an account.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        }
    }

}



