package Taller0y1.reto2

import Taller0y1.notif

class Album {

    //Atributos
    var titulo: String = "None"
    var genero: String = "None"
    var numero_canciones: Int = 0
    var cancion_mas_popular: String = "None"
    var canciones: MutableList<Cancion> = mutableListOf()


    constructor()

    fun pedirDatos() {
        println("Porfavor ingrese el nombre del album")
        this.titulo = readln()
        println("Porfavor ingrese el género")
        this.genero = readln()

        println("Porfavor ingrese el numero de canciones")
        this.numero_canciones = readln().toInt()
        agregar_cancion()


    }

    fun agregar_cancion() {
        var nueva: Int = 0

        println("Si quiere agregar una canción al album escriba 1, de lo contrario escriba 0.")
        nueva = readln().toInt()
        while (nueva == 1) {

            val cancion = Cancion()
            cancion.pedirDatos()
            cancion.imprimir()
            this.canciones.add(cancion)
            print("Si quiere agregar una canción al album escriba 1, de lo contrario escriba 0.")
            nueva = readln().toInt()


        }
        this.numero_canciones = this.canciones.size


    }

    fun mas_popular() {
        var max:Int= 0
        var top:String = "None"
        if(this.canciones.size==0){
            this.cancion_mas_popular = "Ninguna"
        }
        for (cancion in this.canciones) {
            if(cancion.reproducciones>max){

                max = cancion.reproducciones
                top = cancion.titulo
            }
        }
        this.cancion_mas_popular = top

    }

    fun imprimir(){
        println("El album es ${this.titulo}, del género ${this.genero}.Tiene  ${this.numero_canciones} canciones y la más popular es  ${this.cancion_mas_popular} ")


    }

}


    fun main() {

        val album = Album()
        album.pedirDatos()
        album.mas_popular()
        album.imprimir()

    }