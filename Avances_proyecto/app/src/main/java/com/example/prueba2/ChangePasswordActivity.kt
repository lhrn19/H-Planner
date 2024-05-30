package com.example.prueba2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password)

        auth = FirebaseAuth.getInstance()

        val editTextCurrentPassword: EditText = findViewById(R.id.editText_confirm_password_update)
        val editTextNewPassword: EditText = findViewById(R.id.editText_new_password)
        val editTextConfirmNewPassword: EditText = findViewById(R.id.editText_confirm_new_password)
        val buttonChangePassword: Button = findViewById(R.id.change_password)

        buttonChangePassword.setOnClickListener {
            val currentPassword = editTextCurrentPassword.text.toString()
            val newPassword = editTextNewPassword.text.toString()
            val confirmNewPassword = editTextConfirmNewPassword.text.toString()

            if (currentPassword.isNotEmpty() && newPassword.isNotEmpty() && confirmNewPassword.isNotEmpty()) {
                changePassword(currentPassword, newPassword, confirmNewPassword)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun changePassword(currentPassword: String, newPassword: String, confirmNewPassword: String) {
        val user = auth.currentUser

        if (user != null && user.email != null) {
            val credential = EmailAuthProvider.getCredential(user.email!!, currentPassword)

            user.reauthenticate(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user.updatePassword(newPassword).addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            Toast.makeText(this, "Password changed successfully", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Error: ${updateTask.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}



