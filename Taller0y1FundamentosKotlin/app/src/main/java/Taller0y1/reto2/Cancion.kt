package Taller0y1.reto2

class Cancion {
    //Atributos
    var titulo:String ="None"
    var artista:String ="None"
    var year:Int = 0
    var reproducciones:Int = 0
    var popular:Boolean = false
    constructor()


    fun pedirDatos() {
        println("Porfavor ingrese el nombre de la cancion")
        this.titulo = readln()
        println("Porfavor ingrese el nombre del artista")
        this.artista = readln()

        println("Porfavor ingrese el año")
        this.year = readln().toInt()

        println("Porfavor ingrese el num de reproducciones")
        this.reproducciones = readln().toInt()
        if(this.reproducciones>1000){
            this.popular =true

        }
        else{
            this.popular = false


        }





    }
    fun imprimir(){
        println("La cancion es ${this.titulo}, interpretada por ${this.artista}.Se lanzó en ${this.year}, cantidad de reproducciones ${this.reproducciones}. La canción es popular ${this.popular}")


    }


}
fun main() {
    val cancion = Cancion()
    cancion.pedirDatos()
    cancion.imprimir()

}