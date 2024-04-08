package com.example.taller1.reto4
class plato
class menu {
    val menu: MutableMap<String, MutableList<plato>> = mutableMapOf(
        "Entradas" to mutableListOf(),
        "Platos Fuertes" to mutableListOf(),
        "Postres" to mutableListOf(),
        "Bebidas" to mutableListOf()
    )

    fun Añadir_Plato(Categoria: String, Plato:plato) {
        menu[Categoria]?.add(Plato)
    }

    fun Mostrar_Menu() {
        println("Menú del restaurante:")
        for ((Categoria, Platos) in menu) {
            println("$Categoria:")
            Platos.forEach {println("- ${it.Nombre: ${it.Precio}") }
            }
            }

            fun Mostrar_por_categoria(Categoria: String) {
                println("$Categoria:")
                menu[Categoria]?.forEachIndexed { index, Plato ->
                    println("${index + 1}. ${plato.Nombre}: ${Plato.Precio}")
                }
            }

            fun Modificar_Menu() {
                println("Seleccione una categoría para modificar:")
                menu.keys.forEachIndexed { index, Categoria ->
                    println("${index + 1}. $Categoria")
                }
                val Seleccionar_Categoria_Index = readLine()?.toIntOrNull()?.minus(1) ?: return
                val Seleccionar_Categoria = menu.keys.elementAtOrNull(Seleccionar_Categoria_Index) ?: return
                println("Seleccione una acción:")
                println("1. Agregar plato")
                println("2. Modificar plato")
                println("3. Eliminar plato")
                val action = readLine()?.toIntOrNull() ?: return
                when (action) {
                    1 -> Añadir_Plato(Seleccionar_Categoria)
                    2 -> Modificar_Plato(Seleccionar_Categoria)
                    3 -> Remover_Plato(Seleccionar_Categoria)
                    else -> println("Acción no válida")
                }
            }

            fun Añadir_Plato(Categoria: String) {
                println("Ingrese el nombre del nuevo plato:")
                val Nombre = readLine() ?: return
                println("Ingrese el precio del nuevo plato:")
                val Precio = readLine()?.toDoubleOrNull() ?: return
                val Nuevo_Plato = plato(Nombre, Precio)
                Añadir_Plato(Categoria, Nuevo_Plato)
                println("Plato agregado al menú en la categoría $Categoria")
            }

            fun Modificar_Plato(Categoria: String) {
                Mostrar_por_categoria(Categoria)
                println("Seleccione el número del plato a modificar:")
                val Plato_Index = readLine()?.toIntOrNull()?.minus(1) ?: return
                val Platos = menu[Categoria] ?: return
                if (Plato_Index in 0 until Platos.size) {
                    println("Ingrese el nuevo nombre del plato:")
                    val Nuevo_Nombre = readLine() ?: return
                    println("Ingrese el nuevo precio del plato:")
                    val Nuevo_Precio = readLine()?.toDoubleOrNull() ?: return
                    plato[Plato_Index] = plato(Nuevo_Nombre, Nuevo_Precio)
                    println("Plato modificado exitosamente")
                } else {
                    println("Número de plato no válido")
                }
            }

            fun plato_removido(Categoria: String) {
                Mostrar_por_categoria(Categoria)
                println("Seleccione el número del plato a eliminar:")
                val Plato_Index = readLine()?.toIntOrNull()?.minus(1) ?: return
                val Platos = menu[Categoria] ?: return
                if (Plato_Index in 0 until plato.size) {
                    val Plato_Removido = Platos.removeAt(Plato_Index)
                    println("Plato '${plato_removido.Nombre}' eliminado correctamente")
                } else {
                    println("Número de plato no válido")
                }
            }
        }

        fun main() {
            val restaurante = Menu()

            while (true) {
                println("\nSeleccione una opción:")
                println("1. Mostrar menú")
                println("2. Mostrar platos por categoría")
                println("3. Modificar menú")
                println("4. Salir")
                val seleccion = readLine()?.toIntOrNull() ?: continue

                when (seleccion) {
                    1 -> restaurant.showMenu()
                    2 -> {
                        println("Seleccione una categoría:")
                        restaurant.menu.keys.forEachIndexed { index, Categoria ->
                            println("${index + 1}. $Categoria")
                        }
                        val Categoria_Index = readLine()?.toIntOrNull()?.minus(1) ?: continue
                        val Seleccionar_Categoria = restaurant.menu.keys.elementAtOrNull(categoryIndex) ?: continue
                        restaurant.Mostrar_por_categoria(Seleccionar_Categoria)
                    }
                    3 -> restaurant.modifyMenu()
                    4 -> {
                        println("¡Hasta luego!")
                        return
                    }
                    else -> println("Opción no válida")
                }
            }
        }
