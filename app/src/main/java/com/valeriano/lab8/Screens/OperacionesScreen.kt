package com.valeriano.lab8.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.valeriano.lab8.depositarDinero
import com.valeriano.lab8.retirarDinero
import com.valeriano.lab8.saldoGlobal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperacionesScreen(navController: NavController) {
    var mostrarMenu by remember { mutableStateOf(false) }
    var montoRetiro by remember { mutableStateOf("") }
    var montoDeposito by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var esError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Operaciones") },
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
                            onClick = { mostrarMenu = false },
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
                            onClick = { navController.navigate("acerca"); mostrarMenu = false },
                            leadingIcon = { Icon(Icons.Default.Info, null) }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (mensaje.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (esError)
                            MaterialTheme.colorScheme.errorContainer
                        else
                            MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(text = mensaje, modifier = Modifier.padding(16.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Sección Retiros
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Retiros", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = montoRetiro,
                        onValueChange = { montoRetiro = it },
                        label = { Text("Monto a retirar") },
                        leadingIcon = { Icon(Icons.Default.Favorite, null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val monto = montoRetiro.toDoubleOrNull()
                            if (monto != null && monto > 0) {
                                if (retirarDinero(monto)) {
                                    mensaje = "Retiro exitoso: S/ %.2f".format(monto)
                                    esError = false
                                    montoRetiro = ""
                                } else {
                                    mensaje = "Error: Saldo insuficiente"
                                    esError = true
                                }
                            } else {
                                mensaje = "Error: Ingrese un monto válido"
                                esError = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Retirar")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sección Depósitos
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Depósitos", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = montoDeposito,
                        onValueChange = { montoDeposito = it },
                        label = { Text("Monto a depositar") },
                        leadingIcon = { Icon(Icons.Default.Favorite, null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val monto = montoDeposito.toDoubleOrNull()
                            if (monto != null && monto > 0) {
                                depositarDinero(monto)
                                mensaje = "Depósito exitoso: S/ %.2f".format(monto)
                                esError = false
                                montoDeposito = ""
                            } else {
                                mensaje = "Error: Ingrese un monto válido"
                                esError = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Depositar")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Saldo actual: S/ %.2f".format(saldoGlobal.value),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}