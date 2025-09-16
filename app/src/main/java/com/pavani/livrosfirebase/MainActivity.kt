package com.pavani.livrosfirebase


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pavani.livrosfirebase.ui.theme.LivrosFirebaseTheme
import com.pavani.livrosfirebase.view.CadastroLivros
import com.pavani.livrosfirebase.view.DetalhesLivroScreen
import com.pavani.livrosfirebase.view.ListaLivros

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LivrosFirebaseTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "CadastroLivros") {
                    composable("CadastroLivros") {
                        CadastroLivros(navController)
                    }
                    composable("ListaLivros") {
                        ListaLivros(navController)
                    }
                    composable(
                        "DetalhesLivro/{titulo}/{autor}/{genero}",
                        arguments = listOf(
                            navArgument("titulo") { type = NavType.StringType },
                            navArgument("autor") { type = NavType.StringType },
                            navArgument("genero") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val titulo = backStackEntry.arguments?.getString("titulo") ?: ""
                        val autor = backStackEntry.arguments?.getString("autor") ?: ""
                        val genero = backStackEntry.arguments?.getString("genero") ?: ""
                        DetalhesLivroScreen(navController, titulo, autor, genero)
                    }
                }

            }

        }

    }

}

