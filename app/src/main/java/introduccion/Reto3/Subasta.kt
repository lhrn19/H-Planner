package introduccion.Reto3

import introduccion.Reto2.Cancion

class Subasta (val articles: List<Articulo>){
    var offers:MutableMap<Articulo, List<Oferta>> = mutableMapOf()
        get() = field

    fun recibirOferta(article: Articulo, oferente: String, price: Int) {
        offers[article]!!.add(Oferta(oferente, price))
    }

    fun determinarGanador() {
        for (articulo in articles) {
            val ofertasArticulo = offers[articulo]
            if (ofertasArticulo == null || ofertasArticulo.isEmpty()) {
                println("El artículo '$articulo' no ha recibido ofertas y se vende a la casa de subastas por ${articulo.precioBase}€.")
                continue
            }
            val ganador = ofertasArticulo.maxByOrNull { it.precio }!!
            println("El artículo '$articulo' ha sido vendido a ${ganador.oferente} por ${ganador.precio}€.")
        }

    }
}

fun main(){
    val articulos = listOf(
        Articulo("Cuadro", 100),
        Articulo("Escultura", 200),
        Articulo("Jarrón", 50),
    )

    val subasta = Subasta(articulos)

// Ofertas por el cuadro
    subasta.recibirOferta("Cuadro", "Juan", 120)
    subasta.recibirOferta("Cuadro", "María", 150)

// Oferta por la escultura
    subasta.recibirOferta("Escultura", "Pedro", 250)

// Oferta por un artículo inexistente
    subasta.recibirOferta("Jarrón", "Ana", 40)

// Determinar el ganador de cada subasta
    subasta.determinarGanador()
}
