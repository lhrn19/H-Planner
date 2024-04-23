package com.example.prueba2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText


class Login : AppCompatActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var editTextContrasena: EditText
    private lateinit var buttonRegister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextCorreo = findViewById(R.id.editTextCorreo)
        editTextContrasena = findViewById(R.id.editTextContraseña)
        buttonRegister = findViewById(R.id.buttonRegistrar)

        buttonRegister.setOnClickListener{
            val nombre = editTextNombre.text.toString()
            val correo = editTextCorreo.text.toString()
            val contrasena = editTextContrasena.text.toString()

            //Funcion de registra
            RegistrarUsuario(nombre,correo,contrasena)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

fun RegistrarUsuario(nombre:String,correo:String,contrasena:String){
    // Crear un objeto JSON con los datos del usuario
    val jsonUsuario = JSONObject()
    jsonUsuario.put("nombre", nombre)
    jsonUsuario.put("correo", correo)
    jsonUsuario.put("contraseña", contrasena)

    // URL del servidor donde se enviarán los datos (cámbialo por tu URL real)
    val url = "https://ejemplo.com/registrar_usuario"

    // Crear una solicitud POST a la URL del servidor
    val request = JsonObjectRequest(Request.Method.POST, url, jsonUsuario,
        Response.Listener { response ->
            // Manejar la respuesta del servidor aquí (si es necesario)
            // Por ejemplo, puedes mostrar un mensaje de éxito
            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()

            // Guardar los datos en la base de datos local (Room)
            guardarUsuarioLocalmente(nombre, correo, contraseña)
        },
        Response.ErrorListener { error ->
            // Manejar errores de la solicitud (si los hay)
            Toast.makeText(this, "Error al registrar usuario: ${error.message}", Toast.LENGTH_SHORT).show()
        })

    // Agregar la solicitud a la cola de Volley para que se ejecute
    Volley.newRequestQueue(this).add(request)
}