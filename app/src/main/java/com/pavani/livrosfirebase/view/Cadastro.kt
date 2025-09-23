package com.pavani.livrosfirebase.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.pavani.livrosfirebase.datasource.Authentication
import com.pavani.livrosfirebase.ui.theme.Purple40
import com.pavani.livrosfirebase.ui.theme.WHITE
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro(navController: NavController){
    //Variaveis
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmaSenha by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }

    var Auth = Authentication()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu de Opções", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold)
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Login do Usuário") },
                    selected = false,
                    onClick = { navController.navigate("Login") }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Cadastro do Usuário") },
                    selected = false,
                    onClick = { navController.navigate("Cadastro")}
                )
                // ...other drawer items
            }
        }
    ) {

        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Cadastro de Usuário", color = WHITE, fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            },
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Icone do Menu", tint = WHITE, modifier = Modifier.size(36.dp))
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Purple40,
                    )
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { novoEmail ->
                        email = novoEmail
                    },
                    label = {
                        Text("Digite seu email.")
                    }
                )
                OutlinedTextField(
                    value = senha,
                    onValueChange = { novaSenha ->
                        senha = novaSenha
                    },
                    label = {
                        Text("Digite a sua senha.")
                    }
                )
                OutlinedTextField(
                    value = confirmaSenha,
                    onValueChange = { novaSenha ->
                        confirmaSenha = novaSenha
                    },
                    label = {
                        Text("Confirme a sua senha.")
                    }
                )
                Text(mensagem, modifier = Modifier.padding(5.dp), color = Color.Red)
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    onClick = {
                        if (senha == confirmaSenha) {
                            Auth.cadastro(email, senha) { resultado ->
                                if (resultado == "ok") {
                                    navController.navigate("Login") {
                                        popUpTo("Cadastro") { inclusive = true }
                                    }
                                } else {
                                    mensagem = resultado
                                }
                            }
                        }
                    }
                ) {
                    Text("Cadastrar")
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text("Faça o Login aqui!",
                    modifier = Modifier.clickable {
                        navController.navigate("Login")
                    },
                    color = Color.DarkGray
                )
            }
        }
    }
}