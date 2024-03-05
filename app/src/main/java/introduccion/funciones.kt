package introduccion

class funciones {
}

fun Hello(){ println("Hello Camila") }
fun printHello(name:String?):Unit {println("Hi there! ${name}")} //unit no necesaria


fun main(){
    Hello()
    printHello("Camila")
}