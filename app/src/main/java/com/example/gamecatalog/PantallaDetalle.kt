
package com.example.gamecatalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PantallaDetalle(
    videojuegoId: Int,
    onVolver: () -> Unit
) {
    val videojuego = listaVideojuegos.find {
        it.id == videojuegoId
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (videojuego != null) {

            Image(
                painter = painterResource(id = videojuego.imagen),
                contentDescription = "Portada de ${videojuego.titulo}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = videojuego.titulo,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "Categoría: ${videojuego.categoria}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = videojuego.descripcionLarga,
                style = MaterialTheme.typography.bodyLarge
            )

        } else {
            Text(
                text = "Videojuego no encontrado"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para regresar al catálogo
        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al catálogo")
        }
    }
}
