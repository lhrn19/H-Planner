package com.example.prueba2

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class ProductActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var textViewProductId: TextView
    private lateinit var textViewProductName: TextView
    private lateinit var textViewProductPrice: TextView
    private lateinit var textViewProductDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categorybudget)

        textViewProductId = findViewById(R.id.textViewProductId)
        textViewProductName = findViewById(R.id.textViewProductName)
        textViewProductPrice = findViewById(R.id.textViewProductPrice)
        textViewProductDescription = findViewById(R.id.textViewProductDescription)

        val productId = "NwM48IkJ3nxeap5H1Si"


        db.collection("productos")
            .document(productId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val id = document.getString("id")
                    val nombre = document.getString("nombre")
                    val precio = document.getDouble("precio")
                    val descripcion = document.getDouble("descripcion")

                    textViewProductId.text = id
                    textViewProductName.text = nombre
                    textViewProductPrice.text = "$precio"
                    textViewProductDescription.text = descripcion.toString()

                } else {
                    Log.d(TAG, "No se encontró el documento")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error al obtener el documento:", exception)
            }
    }

    companion object {
        private const val TAG = "ProductActivity"
    }
}