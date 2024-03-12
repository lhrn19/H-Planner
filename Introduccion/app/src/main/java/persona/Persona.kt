package persona

class Persona {

    //Atributos
    var nombre:String ="None"
        get() {//consultar,normalmente rtorna un valor
            TODO()
        }

    var edad:Int = 0

        get() {
            TODO()
        }
    var estatura:Double = 0.0
        get() {
            TODO()
        }
    val sexo:Boolean

        get() {
            TODO()
        }
    constructor()
   /* constructor(estatura: Double, sexo:Boolean,edad:Int,nombre:String){
        this.estatura = estatura
        this.sexo = sexo
        this.edad = edad
        this.nombre = nombre

    }*/
    fun pedirDatos(){
        println("Porfavor ingrese su nombre")
        this.nombre = readln()

        println("Porfavor ingrese su edad")
       this.edad = readln().toInt()
    }

}
fun main()

{
val person1 = Persona()
person1.pedirDatos()
person1.edad=10
}