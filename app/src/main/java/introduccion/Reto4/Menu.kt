package introduccion.Reto4

class Menu() {
    var platosPorCategoria: MutableMap<Categoria, MutableList<Plato>> = mutableMapOf()

    // Getter with immutability for better encapsulation
    val allPlatos: Map<Categoria, List<Plato>>
        get() = platosPorCategoria.toMap()

    fun agregarPlato(plato: Plato, cat:Categoria){
        if (platosPorCategoria.containsKey(cat)) {
            platosPorCategoria[cat]?.add(plato)
        } else {
            platosPorCategoria[cat] = mutableListOf(plato)
        }
    }

    fun getPlatosByCategoria(categoria: Categoria): List<Plato>? {
        return platosPorCategoria[categoria]
    }

    fun removePlato(plato: Plato): Boolean {
        // Remove the dish from all categories where it exists
        var removed = false
        for (platos in platosPorCategoria.values) {
            removed = platos.remove(plato) || removed
        }
        return removed
    }

    fun modificarMenu(plato: Plato, nuevoNombre: String = "", nuevaDescripcion: String = "", nuevoPrecio: Double? = null, nuevaCategoria: Categoria? = null) {

        // Modify the dish attributes based on provided values
        plato.nombre = nuevoNombre.ifEmpty { plato.nombre }
        plato.descripcion = nuevaDescripcion.ifEmpty { plato.descripcion }
        nuevoPrecio?.let { plato.precio = it }  // Update price if provided
        nuevaCategoria?.let {  // Update category if provided
            val currentCategory = plato.categoria
            platosPorCategoria[currentCategory]?.remove(plato)
            platosPorCategoria.getOrPut(it) { mutableListOf() }.add(plato)
        }
    }

    fun mostrarTodosPlatos() {
        for (categoria in platosPorCategoria.keys) {
            println("**Categoría: ${categoria.cat}**")
            for (plato in platosPorCategoria[categoria]!!) {
                println("- ${plato.nombre}: ${plato.descripcion} - Precio: $${plato.precio}")
            }
        }
    }
}
fun main() {
    val categoriaComida = Categoria("Comida")
    val categoriaBebida = Categoria("Bebida")

    val plato1 = Plato("Pizza", "Pizza de pepperoni", 12.99, categoriaComida)
    val plato2 = Plato("Jugo de naranja", "Jugo natural de naranja", 2.50, categoriaBebida)

    val menu = Menu()

    menu.agregarPlato(plato1, categoriaComida)
    menu.agregarPlato(plato2, categoriaBebida)

    val todosPlatos = menu.mostrarTodosPlatos()

    menu.modificarMenu(plato1, "Pizza Vegetariana", "", 14.50, categoriaComida)

    menu.removePlato(plato2)

    menu.getPlatosByCategoria(categoriaComida)
}