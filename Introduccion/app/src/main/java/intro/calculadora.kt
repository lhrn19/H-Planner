package intro
import kotlin.math.pow
import kotlin.math.sqrt

class calculadora {

}

fun calculador(operacion: Int, a: Double, b: Double){

    when (operacion){
        //Aqui se realiza un switch


        1->   print("La suma es ${a+b}")

        2-> print("La resta es ${a-b}")

        3-> print("La multiplicacion es ${a*b}")

        4-> print("La division es ${a/b}")

        5 -> print("Los números al cuadrado son ${a.pow(2)} y ${b.pow(2)}")

        6 -> print("La raíz de los números son ${sqrt(a)} y ${sqrt(b)}")

        else -> print("No se reconoce la operación")

    }


}
fun main(){
    println("1 para suma,2 para resta,3 para multiplicacion,4 para division, 5 para potencia,6 para raiz")
    calculador(1,2.0,3.0)


}