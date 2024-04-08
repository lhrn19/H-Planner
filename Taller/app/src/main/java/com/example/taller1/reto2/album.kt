package com.example.taller1.reto2

class album {
    var Titulo: String = ""
            get() = field
    var Genero:String = ""
            get() = field
    var Artista:String = ""
            get() = field
    var Año:Int = 0
            get() = field
    var Numero_canciones:Int = 0
            get() = field

    constructor(Titulo:String,Genero:String, Artista:String, Año:Int, Numero_canciones:Int){
        this.Titulo = Titulo
        this.Genero = Genero
        this.Artista = Artista
        this.Año = Año
        this.Numero_canciones = Numero_canciones
    }

    fun Datos(){
        println("Ingrese el nombre del album")
        this.Titulo = readln()
        println("Ingrese el genero del album")
        this.Genero = readln()
        println("Ingrese el numero de canciones en el album")
        this.Numero_canciones = readln()
        println("Ingrese el Artista del album")
        this.Artista = readln()
        println("Ingrese el Año de lanzamiento del album")
        this.Año = readln()
    }


        fun Cancion_Popular(){
            var Cancion_popular:String = ""
            var Top_Reproducciones = 0
            for (cancion in Cancion){
                if(cancion.Reproducciones > Top_Reproducciones){
                    TopReproducciones = cancion.Reproducciones
                    Cancion_Popular = cancion.Titulo
                }
            }
            println("La cancion mas popular es ${Cancion_Popular} con ${Top_Reproducciones} reproducciones")
        }

        fun Popularidad(): Map<String>, List<String>>{
            val popular:MutableList<String = mutableListOf()
            val no_popular:MutableList<String = mutableListOf()
            for(cancion in Cancion){
                if(cancion.Reproducciones > 999){
                    popular.add(cancion.Nombre)
                } else{
                    no_popular.add(cancion.Nombre)
                }
            }
            return Map Of("Popular" to popular, "No popular" to no_popular)
        }


        fun Imprimir_ALbum(){
            println("El album ${this.Titulo} del genero ${this.genero} con ${this.Numero_canciones} lanzado en el año ${this.Año} con su cancion mas popular ${Cancion_popular}")
        }

        fun main(){

            val album = Album()
            album.Datos()
            album.Cancion_Popular()
            album.Imprimir_Album()
        }
}