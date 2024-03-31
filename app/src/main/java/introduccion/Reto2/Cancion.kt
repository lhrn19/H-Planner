package introduccion.Reto2

class Cancion {

    var nombre:String = ""
        get() = field
    var artista:String = ""
        get() = field
    var ano:Int = 0
        get() = field
    var reproducciones:Int=0
        get() = field

    constructor()
    constructor(nombre: String, artista: String, ano: Int, reproducciones: Int) {
        this.nombre = nombre
        this.artista = artista
        this.ano = ano
        this.reproducciones = reproducciones
    }
    fun pedirDatos() {
        println("Ingrese el nombre")
        val n: String = readLine()!!.toString()
        nombre = n
        println("Ingrese el artista")
        val a: String = readLine()!!.toString()
        artista = a
        println("Ingrese el año")
        val an: Int = readLine()!!.toInt()
        ano = an
        println("Ingrese # de reproducciones")
        val r: Int = readLine()!!.toInt()
        reproducciones = r
    }
    fun printValues(){
        println("Nombre: ${nombre.toString()}, Artista: ${artista},Año: ${ano},Reproducciones: ${reproducciones}")
    }


}
fun main(){
    val c1 = Cancion()
    c1.pedirDatos()
    c1.printValues()
}