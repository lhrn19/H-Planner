package introduccion.Reto2

class Album {
    var albumName:String = ""
        get() = field
    var songs:MutableList<Cancion> = mutableListOf()
        get() = field
    var tipo:String = ""
        get() = field
    var numSongs:Int = 0
        get() = field

    constructor()

    /*fun pedirDatos() {
        println("Agrege las canciones del album")
        val c1 = Cancion()
        c1.pedirDatos()
        c1.printValues()
        songs =
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
    }*/

    fun numSongs(){
        println("El album tiene ${numSongs} canciones")
    }
    fun recorrerAlbum() {
        for (cancion in songs) {
            // Access the values of each Cancion object
            println("${cancion.nombre}, interpretada por ${cancion.artista}, se lanzó en ${cancion.ano}, Reproducciones: ${cancion.reproducciones}")
        }
    }

    fun mostPopularSongAlbum(){
        var mostPopularSong: String = ""
        var maxReproducciones = 0
        for (cancion in songs) {
            if (cancion.reproducciones > maxReproducciones) {
                maxReproducciones = cancion.reproducciones
                mostPopularSong = cancion.nombre
            }
        }
        println("${mostPopularSong} con ${maxReproducciones} reproducciones")
    }

    fun categorizeSongs(): Map<String, List<String>> {
        val popularSongs: MutableList<String> = mutableListOf()
        val unpopularSongs: MutableList<String> = mutableListOf()
        for (cancion in songs) {
            if (cancion.reproducciones >= 1000) {
                popularSongs.add(cancion.nombre)
            } else {
                unpopularSongs.add(cancion.nombre)
            }
        }
        return mapOf("Popular" to popularSongs, "Unpopular" to unpopularSongs)
    }

}

fun main(){
    val album1 = Album()
    album1.albumName="album1"
    album1.songs = listOf(
        Cancion("Song 1", "Artist 1", 2020, 1000),
        Cancion("Song 2", "Artist 1", 2022, 500),
        Cancion("Song 3", "Artist 1", 2021, 2000)
    ).toMutableList()
    album1.numSongs = 3
    album1.tipo = "Classical"

    val album2 = Album()
    album2.albumName="album2"
    album2.songs = listOf(
        Cancion("Song 1", "Artist 2", 2020, 1000),
        Cancion("Song 2", "Artist 2", 2022, 500),
        Cancion("Song 3", "Artist 2", 2021, 2000),
        Cancion("Song 4", "Artist 2", 2021, 2000)
    ).toMutableList()
    album2.numSongs = 4
    album2.tipo = "Rap"

    println("Número de canciones del album 1")
    album1.numSongs()
    println("\n Número de canciones del album 2")
    album2.numSongs()
    println("\n Canciones del album 1")
    album1.recorrerAlbum()
    println("\n Canciones del album 1")
    album2.recorrerAlbum()

    println("\n Canción con más reproducciones del album 1")
    album1.mostPopularSongAlbum()
    println("\n Canción con más reproducciones del album 2")
    album2.mostPopularSongAlbum()

    println("\n Categorias del album 1")
    println(album1.categorizeSongs())
    println("\n Categorias del album 2")
    println(album2.categorizeSongs())
}