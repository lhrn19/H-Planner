package com.example.prueba2

public class Usuario() {
    var nombre: String = ""
    var usuarioId: String = ""
    var username: String = ""
    var email: String = ""
    var telefone: String = ""
    var gender: String = ""
    var password: String = ""

    constructor(nombre: String, usuarioId: String, username: String,  email : String, telefone: String, gender: String, password: String) : this() {
        this.nombre = nombre
        this.usuarioId = usuarioId
        this.username = username
        this.email = email
        this.telefone = telefone
        this.gender = gender
        this.password = password
    }

    fun getNombre(): String {
        return nombre
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun getUsername(): String {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun getEmail(): String {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getTelefone(): String {
        return telefone
    }

    fun setTelefone(telefone: String) {
        this.telefone = telefone
    }

    fun getGender(): String {
        return gender
    }

    fun setGender(gender: String) {
        this.gender = gender
    }

}