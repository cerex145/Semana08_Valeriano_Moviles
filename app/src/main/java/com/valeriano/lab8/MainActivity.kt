package com.valeriano.lab8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.valeriano.lab8.Screens.AcercaDeScreen
import com.valeriano.lab8.Screens.HistorialScreen
import com.valeriano.lab8.Screens.InicioScreen
import com.valeriano.lab8.Screens.OperacionesScreen

// Variables globales para el saldo y transacciones
var saldoGlobal = mutableStateOf(5000.0)
val transaccionesGlobales = mutableStateListOf<String>()

// Función para retirar dinero
fun retirarDinero(monto: Double): Boolean {
    return if (monto > 0 && monto <= saldoGlobal.value) {
        saldoGlobal.value = saldoGlobal.value - monto
        val fecha = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        transaccionesGlobales.add(0, "Retiro|$monto|$fecha")
        true
    } else {
        false
    }
}

// Función para depositar dinero
fun depositarDinero(monto: Double): Boolean {
    return if (monto > 0) {
        saldoGlobal.value = saldoGlobal.value + monto
        val fecha = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        transaccionesGlobales.add(0, "Depósito|$monto|$fecha")
        true
    } else {
        false
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "inicio") {
                    composable(route = "inicio") {
                        InicioScreen(navController)
                    }
                    composable(route = "operaciones") {
                        OperacionesScreen(navController)
                    }
                    composable(route = "historial") {
                        HistorialScreen(navController)
                    }
                    composable(route = "acerca") {
                        AcercaDeScreen(navController)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}