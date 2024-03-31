package Taller0y1

class reto1 {

}
fun notif(numnotf: Int): String{
    var mensaje: String = ""

    if (numnotf > 99) {

        mensaje = "usted tiene más 99+ notificaciones"

    }
    else if(numnotf==0){
        mensaje ="no tiene notificaciones"

    }
    else {

        mensaje ="usted tiene "+ numnotf+ " notificaciones"
    }

    return mensaje
}
fun main() {
    print("Bienvenido, ingrese la cantidad de notificaciones que tiene")
    var numnotf: Int = readln().toInt()
    println(notif((numnotf)))



    println("Si desea ingresar un nuevo mensaje escriba 1, de lo contrario para acabar el programa ingrese 0")
    var nuevo_mensaje: Int = readln().toInt()
    var mensaje: String = ""

    while (nuevo_mensaje==1){
        numnotf  += 1
        println(notif((numnotf)))

        print("Si desea ingresar un nuevo mensaje escriba 1, de lo contrario para acabar el programa ingrese 0")
        nuevo_mensaje = readln().toInt()




    }




}