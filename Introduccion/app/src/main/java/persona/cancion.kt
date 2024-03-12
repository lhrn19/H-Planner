package persona

class Cancion {
    //Atributos
    var titulo:String ="None"
        get() {//consultar,normalmente rtorna un valor
            TODO()
        }

    var artista:String ="None"

        get() {
            TODO()
        }
    var year:Int = 0
        get() {
            TODO()
        }
    var reproducciones:Int = 0

        get() {
            TODO()
        }
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
    }

}
fun main()

{
    val micancion = Cancion()
    micancion.pedirDatos()
    micancion.year=2010
}