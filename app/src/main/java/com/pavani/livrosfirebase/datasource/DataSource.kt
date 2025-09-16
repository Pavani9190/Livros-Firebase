package com.pavani.livrosfirebase.datasource

import com.google.firebase.firestore.FirebaseFirestore

class DataSource {

    private val db = FirebaseFirestore.getInstance()

    fun salvarLivro(titulo: String, autor: String, genero: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val livroMap = hashMapOf(
            "titulo" to titulo,
            "autor" to autor,
            "genero" to genero
        )

        db.collection("Livros")
            .document(titulo)
            .set(livroMap)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e) }
    }

    fun listarLivros(onResult: (List<Map<String, Any>>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("Livros")
            .get()
            .addOnSuccessListener { result ->
                val lista = result.mapNotNull { it.data }
                onResult(lista)
            }
            .addOnFailureListener { e -> onFailure(e) }
    }

    fun deletarLivro(titulo: String) {
        db.collection("Livros").document(titulo).delete()
    }
}
