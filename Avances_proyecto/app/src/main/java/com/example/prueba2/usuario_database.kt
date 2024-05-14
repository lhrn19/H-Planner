package com.example.prueba2

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


object Usuarios {
    // acceder a la base de datos
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val usuariosref: DatabaseReference = database.getReference("Usuarios_Hi_planner")

    //crear usuarios en la base de datos

    fun crearUsuario(usuario: Usuario, callback: (Boolean) -> Unit) {
        usuariosref.push().setValue(usuario)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true) // Operación exitosa
                } else {
                    callback(false) // Operación fallida
                }
            }
    }

    //modificar/actualizar usuarios en la base de datos
    fun actualizarUsuario(usuarioId: String, nuevoUsuario: Usuario, callback: (Boolean) -> Unit) {
        usuariosref.child(usuarioId).setValue(nuevoUsuario)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true) // Operación exitosa
                } else {
                    callback(false) // Operación fallida
                }
            }
    }

    //eliminar usuario en la base de datos
    fun eliminarUsuario(usuarioId: String, callback: (Boolean) -> Unit) {
        usuariosref.child(usuarioId).removeValue()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true) // Operación exitosa
                } else {
                    callback(false) // Operación fallida
                }
            }
    }

    fun consultarUsuarioPorUsuario(username: String, callback: (Usuario?) -> Unit) {
        usuariosref.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val usuario = snapshot.children.firstOrNull()?.getValue(Usuario::class.java)
                    callback(usuario)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(null)
                }
            })
    }
}

