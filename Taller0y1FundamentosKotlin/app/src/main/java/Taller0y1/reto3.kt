package Taller0y1

class reto3 {
}

fun subasta(elemento:String): String {
    println("Se subastará ${elemento}. Primero ingrese el precio mínimo del artículo")
    var minimo: Int = readln().toInt()
    println("Muchas gracias, ahora que se abra la subasta!")
    println("Nombre del ofertante")
    var nombre: String = readln().lowercase()
    val ofertas = mutableListOf<Pair<String, Double>>()
    while (nombre != "no") {
        println("Monto que oferta")
        var oferta: Double = readln().toDouble()
        val aux = Pair(nombre, oferta)
        ofertas.add(aux)
        println("Nombre del ofertante")
        nombre = readln().lowercase()

    }
    if (ofertas.size == 0) {
        return "no hubo ofertas, se vende a la casa por el mínimo ${minimo.toString()}"
    }

    var mx: Double = 0.0
    var persona: String = ""
    for (i in ofertas) {

        if (i.second > mx) {
            persona = i.first
            mx = i.second

        }


    }
    return "se subasta ${elemento} a ${persona} por ${mx}"
}





fun main(){

    println("Bienvenido a la subasta.Ingrese el número de artículos que quiere subastar")
    var num_articulos: Int = readln().toInt()
    var articulo: String = ""
    for(i in 1..num_articulos){
        println("Ingrese su articulo")
        articulo = readln().lowercase()

         println(subasta(articulo))

    }








}