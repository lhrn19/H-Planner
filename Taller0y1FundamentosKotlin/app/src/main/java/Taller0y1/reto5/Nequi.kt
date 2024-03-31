package Taller0y1.reto5
import kotlin.random.Random
class Nequi {
    var clave:Int =1234
    var celular:Long = 3111111111
    var saldo:Double = 45000.0
    constructor()

    fun ingreso(): Boolean{
        var intento:Int = 4
        println("Bienvenido a nequi.")
        while(intento!=0){

            println("Ingrese su celular")
            var celulari:Long = readln().toLong()
            println("Ingrese su clave")
            var clavei:Int = readln().toInt()
            if (clavei == this.clave && celulari == this.celular)
            { println("Bienvenido! tu saldo es ${this.saldo}")
                return true
            }else {
                intento-=1
                println("Upps! Parece que tus datos de acceso no son correctos, Tienes ${intento.toString()} intentos más ")


            }

        }
        return false
    }

    fun saca():Double{
        println("Escribe cajero o fisico")
        var opcion:String = readln().lowercase()
        if (this.saldo<2000){
            println("No te alcanza")
            return this.saldo

        }else{
            println("Cuanto quieres retirar")
            var retiro:Double = readln().toDouble()
            if(retiro>this.saldo){
                println("No te alcanza")
                return this.saldo
            }else{
                val random = Random.nextInt(100000, 999999)
                println("Tu código es ${random}.")
                return this.saldo-retiro
            }
        }



    }
    fun envia():Double{
        println("Escribe el número al que deseas enviar")
        var opcion:Long = readln().toLong()
        println("Cuanto quieres enviar")
        var retiro:Double = readln().toDouble()
        if(retiro>this.saldo){
                println("No te alcanza")
                return this.saldo
        }else{

                println("Dinero enviado a ${opcion}")
                return this.saldo-retiro
        }

    }

    fun recarga():Double{
        println("Escribe el valor a recargar")
        var opcion:Double = readln().toDouble()
        println("Para confirmar la recarga de ${opcion.toString()} escriba si")
        var respuesta:String = readln().lowercase()
        if(respuesta!="si"){
            println("No confirmaste")
            return this.saldo
        }else{

            println("Haz recargado ${opcion}")
            return this.saldo+opcion
        }

    }

    fun wrapper():Boolean{
        if (ingreso()){
            var accion:String = "None"
            while (true) {
            print("¿Que desea hacer? Escribe saca,recarga,envia,salir")
            accion = readln().lowercase()
            when (accion) {
                "saca" -> {

                    this.saldo = this.saca()
                    println("Tu nuevo saldo es ${this.saldo}")

                }
                "recarga" -> {
                    this.saldo = this.recarga()
                    println("Tu nuevo saldo es ${this.saldo}")
                }
                "envia" -> {
                    this.saldo = this.envia()
                    println("Tu nuevo saldo es ${this.saldo}")
                }
                "salir" -> return true
                else -> {
                    println("escoge una opción correcta")
                    return false

                }

                }




        }
        }
        return false


    }


}

fun main(){
    println("Bienvenido a nequi, el celular registrado es 3111111111 con clave 1234")
    var cuenta: Nequi = Nequi()
    cuenta.wrapper()


}