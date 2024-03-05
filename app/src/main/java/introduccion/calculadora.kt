package introduccion

import kotlin.math.pow
import kotlin.math.sqrt

class calculadora {
}

fun sum(int1:Int,int2:Int){
    println("La suma entre ${int1} y ${int2} es ${int1+int2}")
}

fun rest(int1:Int,int2:Int){
    println("La resta entre  ${int1} y ${int2} es ${int1-int2}")
}

fun mul(int1:Int,int2:Int){
    println("La multiplicación de ${int1} y ${int2} es ${int1*int2}")
}

fun main(){
    println("Escoge una operación: Sumq(1),Resta(2),Multiplicar(3)")
    val op: Int = readLine()!!.toInt()

    println("Ingrese su primer número (diferente a cero)")
    var x1:Int = readLine()!!.toInt()

    println("Ingrese su segundo número (diferente a cero)")
    var x2:Int = readLine()!!.toInt()


    when(op) {
        1 -> sum(x1,x2)
        2 -> rest(x1,x2)
        3 -> mul(x1,x2)
        else -> println("La operación esta fuera de rango")
    }

}