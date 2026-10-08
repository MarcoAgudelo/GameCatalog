
package com.example.gamecatalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaCatalogo(
    onVideojuegoClick: (Int) -> Unit
) {
    // Texto que escribe el usuario
    var busqueda by rememberSaveable {
        mutableStateOf("")
    }

    // Categoria seleccionada
    var categoriaSeleccionada by rememberSaveable {
        mutableStateOf("Todos")
    }

    val categorias = listOf(
        "Todos",
        "Acción",
        "Aventura",
        "Deportes"
    )

    // Filtrar por nombre y categoria
    val videojuegosFiltrados = listaVideojuegos.filter { videojuego ->

        val coincideBusqueda =
            videojuego.titulo.contains(
                busqueda,
                ignoreCase = true
            ) ||
                    videojuego.categoria.contains(
                        busqueda,
                        ignoreCase = true
                    )

        val coincideCategoria =
            categoriaSeleccionada == "Todos" ||
                    videojuego.categoria == categoriaSeleccionada

        coincideBusqueda && coincideCategoria
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Catálogo de videojuegos",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            label = { Text("Buscar videojuego") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            singleLine = true
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categorias.forEach { categoria ->

                FilterChip(
                    selected = categoriaSeleccionada == categoria,
                    onClick = {
                        categoriaSeleccionada = categoria
                    },
                    label = {
                        Text(categoria)
                    }
                )
            }
        }

        Text(
            text = "Videojuegos encontrados: ${videojuegosFiltrados.size}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        LazyColumn {
            items(
                items = videojuegosFiltrados,
                key = { it.id }
            ) { videojuego ->

                TarjetaVideojuego(
                    videojuego = videojuego,
                    onClick = {
                        onVideojuegoClick(videojuego.id)
                    }
                )
            }
        }
    }
}
