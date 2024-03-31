package introduccion.Reto1

class notificacion(val mensage: String, var num: Int) {
    fun newNotification() {
        num++
    }
}

fun notificationsTotal(notifications: List<notificacion>){
    val totalNotifications = notifications.sumBy { it.num }
    when {
        totalNotifications == 0 -> println("No hay mensajes disponibles")
        totalNotifications < 100 -> println("Tienes ${totalNotifications} notificaciones")
        else -> println("Tienes 99+ notificaciones")
    }
}

fun main() {
    // Lista de notificaciones de ejemplo
    val notificaciones = listOf(
        notificacion("Mensaje 1", 2),
        notificacion("Mensaje 2", 1),
        notificacion("Mensaje 3", 5),
    )

    // Aumentar la cantidad de notificaciones para el primer mensaje
    notificaciones[0].newNotification()

    // Obtener el resumen de las notificaciones
    notificationsTotal(notificaciones)
}