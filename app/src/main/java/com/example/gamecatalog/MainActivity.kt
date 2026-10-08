
package com.example.gamecatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamecatalog.ui.theme.GameCatalogTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GameCatalogTheme {

                val navController = rememberNavController()

                Scaffold { innerPadding ->

                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        NavHost(
                            navController = navController,
                            startDestination = "catalogo"
                        ) {

                            composable("catalogo") {
                                PantallaCatalogo(
                                    onVideojuegoClick = { id ->
                                        navController.navigate("detalle/$id") {
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }

                            composable("detalle/{id}") { backStackEntry ->

                                val id = backStackEntry.arguments
                                    ?.getString("id")
                                    ?.toIntOrNull() ?: -1

                                PantallaDetalle(
                                    videojuegoId = id,
                                    onVolver = {
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
