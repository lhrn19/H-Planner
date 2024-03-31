package introduccion.Reto3

import introduccion.Reto2.Cancion

class Subasta (val articles: List<Articulo>){
    var offers:MutableMap<Articulo, List<Oferta>> = mutableMapOf()
        get() = field

    fun recibirOferta(article: Articulo, oferente: String, price: Int) {
        val ofertasArticulo:MutableList<Oferta> = mutableListOf()
        ofertasArticulo.add(Oferta(oferente, price))
        offers[article] = ofertasArticulo
    }

    fun determinarGanador() {
        for (articulo in articles) {
            val ofertasArticulo = offers[articulo]
            if (ofertasArticulo == null || ofertasArticulo.isEmpty()) {
                println("El artículo '$articulo' no ha recibido ofertas y se vende a la casa de subastas por ${articulo.basePrice}€.")
                continue
            }
            val ganador = ofertasArticulo.maxByOrNull { it.precio }!!
            println("El artículo ${articulo.nombre} ha sido vendido a ${ganador.oferente} por ${ganador.precio}€.")
        }

    }
}

fun main(){
    val a1 = Articulo("Cuadro", 100)
    val a2 =Articulo("Escultura", 200)
    val a3 =Articulo("Jarrón", 50)
    val articulos = listOf(a1,a2,a3)

    val subasta = Subasta(articulos)

// Ofertas por el cuadro
    subasta.recibirOferta(a1, "Juan", 120)
    subasta.recibirOferta(a1, "María", 150)

// Oferta por la escultura
    subasta.recibirOferta(a2, "Pedro", 250)

// Oferta por un artículo inexistente
    subasta.recibirOferta(a3, "Ana", 40)

// Determinar el ganador de cada subasta
    subasta.determinarGanador()
}
