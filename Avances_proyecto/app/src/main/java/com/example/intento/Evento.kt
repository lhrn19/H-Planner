package com.example.intento;
import java.util.Date

data class Evento(
    var Id: String?= null,
    var nombre_ingresado: String?= null,
    var calendario_ingresado: String? = null,
    var tiempo_reminder_ingresado: Int? = null,
    var allDay_ingresado: Boolean= false,
    var recordatorio_ingresado: String? = null,
    var from_hora:String = "00:00",
    var to_hora:String = "00:00",
    var lunes_ingresado: Boolean= false,
    var martes_ingresado: Boolean= false,
    var miercoles_ingresado: Boolean = false,
    var jueves_ingresado: Boolean = false,
    var viernes_ingresado: Boolean = false,
    var sabado_ingresado: Boolean = false,
    var domingo_ingresado: Boolean = false,
    var state:String = "not started"



)