package com.pavani.livrosfirebase.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesLivroScreen(
    navController: NavController,
    titulo: String,
    autor: String,
    genero: String
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("📚 Menu Livros", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = { Text("Lista de Livros") },
                    selected = false,
                    onClick = { navController.navigate("ListaLivros") }
                )
                NavigationDrawerItem(
                    label = { Text("Cadastro de Livros") },
                    selected = false,
                    onClick = { navController.navigate("CadastroLivros") }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Detalhes do Livro") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text("Título:", fontWeight = FontWeight.Medium)
                Text(titulo, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Text("Autor:", fontWeight = FontWeight.Medium)
                Text(autor, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Text("Gênero:", fontWeight = FontWeight.Medium)
                Text(genero, fontWeight = FontWeight.Bold)
            }
        }
    }
}
