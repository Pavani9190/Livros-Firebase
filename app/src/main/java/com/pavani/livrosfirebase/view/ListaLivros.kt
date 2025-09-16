package com.pavani.livrosfirebase.view

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.pavani.livrosfirebase.datasource.DataSource
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaLivros(navController: NavController) {
    val dataSource = DataSource()
    var listaLivros by remember { mutableStateOf(listOf<Map<String, Any>>()) }
    var mensagem by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    LaunchedEffect(Unit) {
        dataSource.listarLivros(
            onResult = { listaLivros = it },
            onFailure = { e -> mensagem = "Erro: ${e.message}" }
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "📚 Menu do App Livros",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                NavigationDrawerItem(
                    label = { Text("Lista de Livros") },
                    selected = true,
                    onClick = { }
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
                    title = { Text("Lista de Livros") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate("CadastroLivros") }) {
                    Icon(Icons.Filled.Add, contentDescription = "Cadastro de livros")
                }
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                items(listaLivros) { livro ->
                    val titulo = livro["titulo"] as? String ?: "Sem título"
                    val autor = livro["autor"] as? String ?: "Desconhecido"
                    val genero = livro["genero"] as? String ?: "-"
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {
                                val encodedTitulo = Uri.encode(titulo)
                                val encodedAutor = Uri.encode(autor)
                                val encodedGenero = Uri.encode(genero)
                                navController.navigate("DetalhesLivro/$encodedTitulo/$encodedAutor/$encodedGenero")
                            },
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F4F6)),
                        elevation = CardDefaults.cardElevation(6.dp),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(titulo, fontWeight = FontWeight.Bold)
                                Text("Autor: $autor", color = Color.DarkGray)
                                Text("Gênero: $genero", color = Color.Gray)
                            }
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Deletar",
                                tint = Color(0xFFD32F2F),
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable {
                                        dataSource.deletarLivro(titulo)
                                        listaLivros = listaLivros.filter { it["titulo"] != titulo }
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

