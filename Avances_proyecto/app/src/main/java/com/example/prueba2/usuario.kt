package com.example.prueba2

public class Usuario() {
    var nombre: String = ""
    var username: String = ""
    var email: String = ""
    var birthday: String = ""
    var telefone: String = ""
    var gender: String = ""
    var password: String = ""

    constructor(
        nombre: String,
        username: String,
        email: String,
        birthday: String,
        telefone: String,
        gender: String,
        password: String
    ) : this() {
        this.nombre = nombre
        this.username = username
        this.email = email
        this.birthday = birthday
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

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }
}
