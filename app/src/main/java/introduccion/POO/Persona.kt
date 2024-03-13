package introduccion.POO

class Persona (nombre:String,edad:Int,estatura:Double,sexo:Boolean){
    // Attributes
    var nombre:String  = nombre
        get() = field
        set(value) {field = value}


    var edad:Int  = edad
        get() = field
        set(value) {field = value}

    var estatura:Double  = estatura
        get() = field
        set(value) {field = value}

    var sexo:Boolean  = sexo
        get() = field
        set(value) {field = value}

    fun pedirDatos(){
        println("Ingrese su nombre")
        nombre
        println("Ingrese su edad")
        edad
        println("Ingrese su estatura")
        estatura
        println("Ingrese su sexo (true - Mujer, false - Homvre)")
        sexo

    }

}

fun main(){
    val P1 = Persona("Camila",22,60.5,true)
    P1.pedirDatos()
}