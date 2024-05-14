package com.example.prueba2

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba2.Usuario
import com.example.prueba2.Usuarios

class AccountManagerActivity(private val usuario: Usuario) : AppCompatActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextUsuario: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextTelefone: EditText
    private lateinit var editTextContraseña: EditText
    private lateinit var editTextGenero: EditText
    private lateinit var btnCreate_account: Button

    private lateinit var editTextNombre2: EditText
    private lateinit var editTextUsuario2: EditText
    private lateinit var editTextBirthday: EditText
    private lateinit var editTextTelefone2: EditText
    private lateinit var editTextGender2: EditText
    private lateinit var btnActualizarUsuario: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_account)

        // Inicialización de los componentes de la vista para crear cuenta
        editTextNombre = findViewById(R.id.editText_username_na)
        editTextUsuario = findViewById(R.id.editText_name_na)
        editTextEmail = findViewById(R.id.editText_email_na)
        editTextTelefone = findViewById(R.id.editText_telefone_na)
        editTextContraseña = findViewById(R.id.editText_password_na)
        editTextGenero = findViewById(R.id.editText_gender_na)
        btnCreate_account = findViewById(R.id.create_account)

        btnCreate_account.setOnClickListener {
            crearUsuario()
        }

        // Inflar e inicializar los componentes del layout para actualizar usuario
        val inflater = LayoutInflater.from(this)
        val actualizarUsuarioLayout = inflater.inflate(R.layout.edit_profile_information, null)

        editTextNombre2 = actualizarUsuarioLayout.findViewById(R.id.editText_name_upi)
        editTextUsuario2 = actualizarUsuarioLayout.findViewById(R.id.editText_username_upi)
        editTextBirthday = actualizarUsuarioLayout.findViewById(R.id.editText_birthday_upi)
        editTextTelefone2 = actualizarUsuarioLayout.findViewById(R.id.editText_telefone_upi)
        editTextGender2 = actualizarUsuarioLayout.findViewById(R.id.editText_gender_upi)
        btnActualizarUsuario = actualizarUsuarioLayout.findViewById(R.id.update)

        btnActualizarUsuario.setOnClickListener {
            actualizarUsuario()
        }
    }

    private fun crearUsuario() {
        val nombre = editTextNombre.text.toString().trim()
        val usuario = editTextUsuario.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val telefono = editTextTelefone.text.toString().trim()
        val genero = editTextGenero.text.toString().trim()
        val contraseña = editTextContraseña.text.toString().trim()

        if (nombre.isEmpty() || usuario.isEmpty() || email.isEmpty() || telefono.isEmpty() || genero.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevoUsuario = Usuario(nombre, usuario, telefono, email, genero, contraseña)
        Usuarios.crearUsuario(nuevoUsuario) { exito ->
            if (exito) {
                Toast.makeText(this, "Usuario agregado correctamente", Toast.LENGTH_SHORT).show()
                limpiarCampos()
            } else {
                Toast.makeText(this, "Error al crear el usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun limpiarCampos() {
        editTextNombre.text.clear()
        editTextUsuario.text.clear()
        editTextEmail.text.clear()
        editTextTelefone.text.clear()
    }

    private fun actualizarUsuario() {
        val nombre = editTextNombre2.text.toString().trim()
        val usuario = editTextUsuario2.text.toString().trim()
        val birthday = editTextBirthday.text.toString().trim()
        val telefono = editTextTelefone2.text.toString().trim()

        if (nombre.isEmpty() || usuario.isEmpty() || birthday.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        Usuarios.consultarUsuarioPorUsuario(nombre) { usuario ->
            if (usuario != null) {
                val actualizarUsuario = Usuario(nombre, usuario, birthday, telefono)
                Usuarios.actualizarUsuario(usuario.usuarioId, actualizarUsuario) { exito ->
                    if (exito) {
                        Toast.makeText(
                            this,
                            "Profile information successfully updated",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "Error updating profile information",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(this, "No se encontró el usuario a modificar", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}