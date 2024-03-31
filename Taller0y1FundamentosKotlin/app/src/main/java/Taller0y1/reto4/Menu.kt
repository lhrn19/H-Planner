package Taller0y1.reto4

class Menu {


    val entradas = mutableMapOf<Int, Plato>()
    val fuertes = mutableMapOf<Int, Plato>()
    val bebidas = mutableMapOf<Int, Plato>()
    val postres = mutableMapOf<Int, Plato>()
    constructor()

    fun agregar(categoria:String,nombre_plato:String){
        val diccionario = when (categoria) {
            "entrada" -> entradas
            "fuerte" -> fuertes
            "bebida" -> bebidas
            "postre" -> postres
            else -> {
                println("Categoría incorrecta")
                return
            }
        }

        print("A continuacion escriba los siguientes datos del plato. Nombre")
        var nombre: String = readln().lowercase()
        print("Descripción")
        var des: String = readln().lowercase()
        print("Precio")
        var precio: Double = readln().toDouble()
        print("Código")
        var cod: Int = readln().toInt()
        var platoaux: Plato = Plato(nombre, des, precio, cod)
        diccionario[cod] = platoaux
    }


    fun mostrar_todos(){
        println("Entradas:")
        for ((cod,plato) in entradas) {
            plato.imprimir()
            println("----------")
        }
        println("Bebidas:")
        for ((cod,plato) in bebidas) {
            plato.imprimir()
            println("----------")
        }
        println("Fuertes:")
        for ((cod,plato) in fuertes) {
            plato.imprimir()
            println("----------")
        }
        println("Postres:")
        for ((cod,plato) in postres) {
            plato.imprimir()
            println("----------")
        }
    }
    fun eliminar(categoria:String,cod:Int){
        val diccionario = when (categoria) {
            "entrada" -> entradas
            "fuerte" -> fuertes
            "bebida" -> bebidas
            "postre" -> postres
            else -> {
                println("Categoría incorrecta")
                return
            }
        }
        diccionario.remove(cod)
    }

    fun mostrar(categoria:String,cod:Int){
        val dic = when (categoria) {
            "entrada" -> entradas
            "fuerte" -> fuertes
            "bebida" -> bebidas
            "postre" -> postres
            else -> {
                println("Categoría incorrecta")
                return
            }
        }
        val plato = dic[cod]
        plato!!.imprimir()
    }




}
fun main() {
    var menu1 = Menu()
    println("Bienvenido a su menú,escriba el ombre de lso platos en minúscula")

    println("Agregemos una ensalada cesar a las entradas")
    menu1.agregar("entrada", "Ensalada cesar")

    var carne: Plato = Plato("carne", "deliciosa carne asada", 23.900, 234)
    menu1.fuertes[234] = carne
    var helado: Plato = Plato("helado", "helado de vainilla tradicional", 3.900, 232)
    menu1.postres[232] = helado
    var tiramisu: Plato = Plato("tiramisu", "tiramisu de café", 5.900, 235)
    menu1.postres[235] = tiramisu
    println("Agregemos una Limonada a bebidas")
    menu1.agregar("bebida", "Limonada")
    println("Veamos todo el menú")
    menu1.mostrar_todos()

    println("Ahora eliminemos el helado y modifiquemos la descripción de pescado")
    menu1.eliminar("postre",232)
    val platoFuerte = menu1.fuertes[234]
    platoFuerte?.cambiar_descripcion("Deliciosa carne al horno")
    menu1.mostrar_todos()
    println(" Ahora veamos solamente el alimento de código 235 de la sección de postres")
    menu1.mostrar("postre",235)
}