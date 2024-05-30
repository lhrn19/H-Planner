package com.example.intento;
import java.util.Date

data class Calendar(
    var Id: String?= null,
    var nombre_ingresado: String?= null,
    var color: String?= "azul_bello",
    var events: Map<String, String> = emptyMap()

)