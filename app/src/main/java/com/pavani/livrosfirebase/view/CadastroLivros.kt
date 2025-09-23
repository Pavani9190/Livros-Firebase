package com.pavani.livrosfirebase.view

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pavani.livrosfirebase.datasource.DataSource
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroLivros(navController: NavController) {
    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val dataSource = DataSource()
    val auth = remember { com.pavani.livrosfirebase.datasource.Authentication() }
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxHeight()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Ícone do App",
                            tint = Color(0xFF6200EE),
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Menu do App Livros",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Text(
                        text = "👤 ${auth.getUserEmail()}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )

                    Divider(modifier = Modifier.padding(vertical = 16.dp))


                    NavigationDrawerItem(
                        label = { Text("➕ Lista dos Livros") },
                        selected = false,
                        onClick = {
                            scope.launch {
                                navController.navigate("ListaLivros")
                                drawerState.close()
                            }
                        },
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                auth.logout()
                                navController.navigate("Login") {
                                    popUpTo("CadastroLivros") { inclusive = true }
                                }
                            }
                            .padding(12.dp)
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Logout",
                            tint = Color(0xFFD32F2F),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Logout",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD32F2F)
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Cadastrar Livro") },
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
                FloatingActionButton(onClick = { navController.navigate("ListaLivros")}) {
                    Icon(Icons.Filled.Menu, contentDescription = "Lista de Livros")
                }
            }
        ) {
            innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it},
                    leadingIcon = { Icon(Icons.Filled.Book, contentDescription = null) },
                    label = {Text("Título")},
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = autor,
                    onValueChange = { autor = it },
                    leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
                    label = { Text("Autor") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = genero,
                    onValueChange = { genero = it },
                    leadingIcon = { Icon(Icons.Filled.Bookmark, contentDescription = null) },
                    label = { Text("Gênero") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        if (titulo.isNotBlank() && autor.isNotBlank() && genero.isNotBlank()) {
                            val tituloParaNavegar = titulo
                            val autorParaNavegar = autor
                            val generoParaNavegar = genero

                            dataSource.salvarLivro(titulo, autor, genero,
                                onSuccess = {
                                    mensagem = "✔ Livro cadastrado com sucesso!"
                                    val encodedTitulo = Uri.encode(tituloParaNavegar)
                                    val encodedAutor = Uri.encode(autorParaNavegar)
                                    val encodedGenero = Uri.encode(generoParaNavegar)
                                    navController.navigate("DetalhesLivro/$encodedTitulo/$encodedAutor/$encodedGenero")

                                    titulo = ""
                                    autor = ""
                                    genero = ""
                                },
                                onFailure = { e -> mensagem = "❌ Erro: ${e.message}" }
                            )
                        } else {
                            mensagem = "❌ Preencha todos os campos."
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Cadastrar", fontWeight = FontWeight.Bold)
                }



                Spacer(Modifier.height(16.dp))
                if (mensagem.isNotBlank()) {
                    Text(
                        mensagem,
                        color = if (mensagem.startsWith("✔")) Color(0xFF388E3C) else Color(0xFFD32F2F),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}