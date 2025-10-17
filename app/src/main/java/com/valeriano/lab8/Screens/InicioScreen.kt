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
import com.valeriano.lab8.saldoGlobal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(navController: NavController) {
    var mostrarMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Cajero Virtual") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
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
                            onClick = {
                                navController.navigate("inicio")
                                mostrarMenu = false
                            },
                            leadingIcon = { Icon(Icons.Default.Home, null) }
                        )
                        DropdownMenuItem(
                            text = { Text("Operaciones") },
                            onClick = {
                                navController.navigate("operaciones")
                                mostrarMenu = false
                            },
                            leadingIcon = { Icon(Icons.Default.AccountCircle, null) }
                        )
                        DropdownMenuItem(
                            text = { Text("Historial") },
                            onClick = {
                                navController.navigate("historial")
                                mostrarMenu = false
                            },
                            leadingIcon = { Icon(Icons.Default.List, null) }
                        )
                        Divider()
                        DropdownMenuItem(
                            text = { Text("Acerca de") },
                            onClick = {
                                navController.navigate("acerca")
                                mostrarMenu = false
                            },
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
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Saldo Actual", fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "S/ %.2f".format(saldoGlobal.value),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { navController.navigate("operaciones") },
                modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp).height(56.dp)
            ) {
                Text("Retirar dinero", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("operaciones") },
                modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp).height(56.dp)
            ) {
                Text("Depositar dinero", fontSize = 18.sp)
            }
        }
    }
}