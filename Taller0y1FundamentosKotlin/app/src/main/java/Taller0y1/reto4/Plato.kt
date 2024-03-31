package Taller0y1.reto4

class Plato(var nombre:String,var descripcion:String,var precio:Double,var codigo:Int) {



    constructor():this("", "", 0.0, 0)

    fun imprimir(){

        println("El plato ${this.nombre} tiene la descripcion ${this.descripcion} tiene un costo de   ${this.precio} y el código es ${this.codigo}")
    }
    fun cambiar_precio(nuevo: Double){
        this.precio = nuevo

    }

    fun cambiar_descripcion(nuevo: String){
        this.descripcion = nuevo

    }

    fun cambiar_nombre(nuevo: String){
        this.descripcion = nuevo

    }
    fun cambiar_codigo(nuevo: Int){
        this.codigo = nuevo

    }

}
fun main(){
    var plato1 = Plato("Bolognesa","deliciosa pasta",30.000,129)
    plato1.cambiar_precio(40.000)
    plato1.imprimir()


}