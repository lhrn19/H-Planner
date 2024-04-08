package com.example.taller1

class reto1 {
    class Notificacion(val mensaje: String, var numero: Int{
        //Funcion para agregar una nueva notificacion
        fun NuevaNotificacion(){
            numero = numero + 1
        }
    }

    //Funcion para ver el total de notificaciones
    fun TotalNotificaciones(notifiacion: List<notificacion>){
        val notificaciones = notificaciones.sumBy {it.num}
        if (notificaciones == 0){
            printIn("No tiene ninguna notificacion")
        }
        else if (notificaciones > 99){
            printIn("Tiene +99 notificaciones)
        }
    }

    fun main() {

        val Notificaciones = listOf(
            notificacion("Whatsapp", 4),
            notificacion("Instagram", 5),
            notiificacion("Urosario", 2),
        )

        Notificaciones[0].NuevaNotificacion()


        TotalNotificaciones(Notificaciones)
    }
}