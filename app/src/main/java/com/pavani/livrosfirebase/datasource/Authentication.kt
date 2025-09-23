package com.pavani.livrosfirebase.datasource

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class Authentication {
    private val auth = Firebase.auth
    val user = auth.currentUser

    fun login(email: String, senha: String, onResult: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Authentication", "Login efetuado com sucesso.")
                    onResult("ok")
                } else {
                    Log.d("Authentication", "Erro durante o login.")
                    onResult(task.exception?.message ?: "Erro desconhecido")
                }
            }
    }

    fun cadastro(email: String, senha: String, onResult: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Authentication", "Conta criada com sucesso.")
                    onResult("ok")
                } else {
                    Log.d("Authentication", "Erro durante a criação da conta.")
                    onResult(task.exception?.message ?: "Erro desconhecido")
                }
            }
    }

    fun verificaLogin(): Boolean {
        return auth.currentUser != null
    }

    fun logout() {
        auth.signOut()
    }

    fun getUserEmail(): String {
        return auth.currentUser?.email ?: "Usuário não logado"
    }
}
