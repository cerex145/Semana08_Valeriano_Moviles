package com.valeriano.lab8.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcercaDeScreen(navController: NavController) {
    var mostrarMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Acerca de") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                actions = {
                    IconButton(onClick = { mostrarMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menú")
                    }

                    DropdownMenu(
                        expanded = mostrarMenu,
                        onDismissRequest = { mostrarMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Inicio") },
                            onClick = { navController.navigate("inicio"); mostrarMenu = false },
                            leadingIcon = { Icon(Icons.Default.Home, null) }
                        )
                        DropdownMenuItem(
                            text = { Text("Operaciones") },
                            onClick = { navController.navigate("operaciones"); mostrarMenu = false },
                            leadingIcon = { Icon(Icons.Default.AccountCircle, null) }
                        )
                        DropdownMenuItem(
                            text = { Text("Historial") },
                            onClick = { navController.navigate("historial"); mostrarMenu = false },
                            leadingIcon = { Icon(Icons.Default.List, null) }
                        )
                        Divider()
                        DropdownMenuItem(
                            text = { Text("Acerca de") },
                            onClick = { mostrarMenu = false },
                            leadingIcon = { Icon(Icons.Default.Info, null) }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text("Mi Cajero Virtual", fontSize = 28.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Versión 1.0", fontSize = 18.sp)

                    Spacer(modifier = Modifier.height(24.dp))

                    Divider()

                    Spacer(modifier = Modifier.height(24.dp))

                    Text("Autor : Carlos Valeriano", fontSize = 16.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Curso TECSUP – Jetpack Compose", fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("BAnca :P© 2025", fontSize = 14.sp)
                }
            }
        }
    }
}