package com.example.taller1.reto2

class clase_cancion {
    var Titulo: String = ""
        get() = field
    var Artista:String = ""
        get() = field
    var Año:Int = 0
        get() = field
    var Reproducciones:Int = 0
        get() = field
    var Popular:Boolean  = false

    constructor(Titulo:String, Artista:String, Año:Int, Reproducciones:Int, Popular:Boolean){
        this.Titulo = Titulo
        this.Artista = Artista
        this.Año = Año
        this.Reproducciones = Reproducciones
        this.Popular = Popular
    }

    fun Datos(){
        println("Ingrese el nombre de la cancion")
        this.Titulo = readln()
        println("Ingrese el numero de Reproducciones de la cancion")
        this.Reproducciones = readln()
        println("Ingrese el Artista de la cancion")
        this.Artista = readln()
        println("Ingrese el Año de lanzamiento de la cancion")
        this.Año = readln()
        if(this.Reproducciones > 1000){
            this.Popular = true
        } else{
            this.Popular = false
        }
    }

    fun Imprimir_Cancion(){
        println("La cancion ${this.Titulo} del artista ${this.Artista} lanzada en el año ${this.Año} con ${this.Reproducciones} lanzada en el Año ${this.Año})
    }

}

fun main() {
    val cancion = Cancion()
    cancion.Datos()
    cancion.Imprimir_Cancion()
}

