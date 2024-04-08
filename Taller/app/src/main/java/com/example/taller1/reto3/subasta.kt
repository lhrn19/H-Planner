package com.example.taller1.reto3

class clase_subasta (val Articulos:List<Articulo>){
    var Ofertas:MutableMap<Articulo, List<Oferta>> = mutableMapOf()
        get() = field

    //Funcion para guardar las ofertas por cada articulo
    fun Oferta_Recibida(Articulo:Articulo, Ofertante:String, Precio:Int){
        val Ofertas_Articulo:MutableList<Oferta> = mutableListOf()
        Ofertas_Articulo.add(Oferta(Ofertante, Precio))
        OFerta_Recibida[Articulo] = Ofertas_Articulo
    }

    //Funcion para determinar el ganador de cada subasta
    fun Ganador(){
        for(Articulo in Articulo){
            val Ofertas_Articulo = Oferta_Recibida[Articulo]
            if (Ofertas_Articulo.isEmpty()){
                printIn("El articulo ${Articulo.Nombre} no ha recibido ofertas y se vende a ${Articulo.Precio_Base}")
            }
            val Ganador = Ofertas_Articulo.maxByOrNull{it.Precio}
            printIn("El articulo ${Articulo.Nombre} se vendio a ${Oferta.Ofertante} por ${Oferta.Precio}")
        }
    }
}

fun main(){
    val a1 = Articulo("Cuadro", 100)
    val Articulo2 =Articulo("Escultura", 200)
    val Articulo3 =Articulo("JarrÃ³n", 50)
    val Articulos = listOf(Articulo1,Articulo2,Articulo3)

    val Subasta = Casa_Subastas(Articulos)

    Subasta.Oferta_Recibida(Articulo1, "Sofia", 500)
    Subasta.Oferta_Recibida(Articulo1, "Tatiana", 550)

    Subasta.Ganador()
}