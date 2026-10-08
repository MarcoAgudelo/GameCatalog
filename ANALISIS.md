# ANÁLISIS DEL TALLER - CLASE 06

**Proyecto:** GameCatalog  
**Asignatura:** Programación para Dispositivos Móviles  
**Lenguaje:** Kotlin  
**Framework:** Jetpack Compose  
**Herramienta:** Android Studio

## 1. Descripción del proyecto

Para este taller desarrollé una aplicación Android llamada GameCatalog, que permite visualizar y consultar un catálogo de videojuegos.

La aplicación contiene 12 videojuegos distribuidos en tres categorías: Acción, Aventura y Deportes. Cada videojuego tiene un identificador, nombre, categoría, descripción corta, descripción larga e imagen.

Para mostrar los videojuegos utilicé LazyColumn y para la navegación entre pantallas implementé Navigation Compose.

También agregué una barra de búsqueda que permite encontrar videojuegos por su nombre o categoría.

## 2. Predicción del problema

Antes de realizar la prueba de navegación, pensé que al presionar rápidamente varias veces una misma tarjeta se podrían abrir varias pantallas de detalles del mismo videojuego.

Esto podría ocasionar que, al utilizar el botón de retroceso, la aplicación mostrara repetidamente la misma pantalla antes de regresar al catálogo.

## 3. Identificación del error

Para comprobar la predicción, ejecuté la aplicación y presioné rápidamente varias veces la tarjeta de Grand Theft Auto V.

Después de ingresar a la pantalla de detalles, presioné el botón de retroceso y observé que aparecía nuevamente la misma pantalla.

Esto confirmó que se estaban acumulando entradas repetidas en el historial de navegación.

El código utilizado inicialmente era:

```kotlin
navController.navigate("detalle/$id")
```

Este código permitía abrir los detalles, pero no tenía ninguna configuración para evitar que se agregara nuevamente el mismo destino al historial.

## 4. Análisis de la causa

El problema estaba relacionado con el funcionamiento del back stack, que es el historial de pantallas utilizado por Navigation Compose.

Cada vez que se ejecutaba la función navigate(), podía agregarse una nueva entrada al historial.

Cuando el usuario presionaba rápidamente una tarjeta, se podían ejecutar varias acciones de navegación antes de que terminara la transición.

Por esta razón, al retroceder, el usuario encontraba pantallas de detalles repetidas.

## 5. Solución implementada

Para corregir el problema, modifiqué la navegación dentro del archivo MainActivity.kt.

Agregué la propiedad launchSingleTop con valor true.

El código quedó así:

```kotlin
navController.navigate("detalle/$id") {
    launchSingleTop = true
}
```

Esta propiedad evita crear una nueva entrada cuando el destino al que queremos navegar ya se encuentra en la parte superior del historial.

Aunque esta configuración no bloquea todas las pulsaciones rápidas posibles, permitió solucionar el comportamiento que había observado durante las pruebas.

## 6. Comprobación de la solución

Después de modificar el código, ejecuté nuevamente la aplicación.

Repetí la prueba presionando rápidamente varias veces la tarjeta de Grand Theft Auto V.

En esta ocasión, al presionar el botón de retroceso, la aplicación regresó directamente al catálogo.

No aparecieron pantallas de detalles duplicadas durante la prueba.

Por lo tanto, la solución funcionó correctamente para el problema identificado.

## 7. Prueba con dos tarjetas diferentes

También realicé una prueba presionando rápidamente dos tarjetas de videojuegos diferentes.

Al hacerlo, la aplicación mostró la pantalla de detalles del segundo videojuego seleccionado.

Después presioné el botón de retroceso y regresé normalmente al catálogo, sin encontrar pantallas adicionales.

Con esta prueba comprobé que la navegación funcionaba correctamente en ese caso.

Es importante aclarar que launchSingleTop evita duplicar el destino que ya está en la parte superior del historial, pero no impide necesariamente navegar hacia destinos diferentes.

## 8. Pruebas de navegación consecutivas

Para verificar la estabilidad de la aplicación, realicé cinco ciclos consecutivos de navegación.

En cada ciclo busqué un videojuego, ingresé a su pantalla de detalles y regresé al catálogo.

Durante estas pruebas comprobé que:

- La aplicación permitía abrir los detalles correctamente.
- El botón de retroceso funcionaba.
- La búsqueda se conservaba al regresar.
- No aparecían pantallas duplicadas.
- Las imágenes se mostraban correctamente.

Los resultados fueron satisfactorios.

## 9. Reto adicional: filtros por categorías

Como reto adicional implementé filtros por categorías utilizando el componente FilterChip de Jetpack Compose.

Agregué cuatro opciones:

- Todos
- Acción
- Aventura
- Deportes

Estos filtros permiten visualizar únicamente los videojuegos de la categoría seleccionada.

También combiné los filtros con la barra de búsqueda, de manera que el usuario puede buscar videojuegos dentro de una categoría específica.

Para conservar la categoría seleccionada utilicé rememberSaveable.

Realicé las siguientes pruebas:

| Prueba | Resultado |
|---|---|
| Seleccionar Acción | 4 videojuegos |
| Seleccionar Aventura | 4 videojuegos |
| Seleccionar Deportes | 4 videojuegos |
| Seleccionar Todos | 12 videojuegos |
| Buscar GTA dentro de Acción | 1 videojuego |
| Abrir detalles y regresar | Conserva el filtro |

Todas las pruebas funcionaron correctamente.

## 10. Mejoras visuales

Para mejorar la presentación de la aplicación agregué imágenes representativas de los 12 videojuegos.

Las imágenes se almacenaron en la carpeta res/drawable del proyecto.

Utilicé los componentes Image y painterResource para mostrarlas.

En el catálogo, las imágenes aparecen junto al nombre, categoría y descripción corta de cada videojuego.

En la pantalla de detalles, la imagen aparece en un tamaño mayor, acompañada de la descripción completa.

Estas mejoras permiten que el catálogo sea más visual y fácil de utilizar.

## 11. Evidencias

Las capturas de pantalla se encuentran en la carpeta evidencias del proyecto.

**Evidencia 1: catalogo_busqueda.png**

Muestra el catálogo con la búsqueda de Minecraft activa. Se observa que el buscador filtra correctamente y presenta un único resultado.

**Evidencia 2: pantalla_detalle.png**

Muestra la pantalla de detalles de Minecraft, incluyendo la imagen, el título, la categoría, la descripción completa y el botón para regresar.

**Evidencia 3: navegacion_corregida.png**

Muestra el catálogo después de regresar desde una pantalla de detalles. La comprobación de que no existían pantallas duplicadas se realizó mediante la prueba de navegación descrita anteriormente.

**Evidencia 4: filtro_categorias.png**

Muestra el filtro Acción seleccionado, con cuatro videojuegos encontrados y sus respectivas imágenes.

El comportamiento del error antes de la corrección quedó documentado mediante la descripción de la prueba realizada.


## 12. Conclusión

Con este taller aprendí a desarrollar una aplicación Android utilizando Kotlin y Jetpack Compose, implementando listas, búsquedas, filtros y navegación entre pantallas.

Una de las partes más importantes fue identificar el problema de navegación duplicada, comprender cómo funciona el back stack y aplicar launchSingleTop para evitar entradas repetidas del mismo destino.

También pude comprobar la importancia de realizar pruebas antes y después de modificar el código, ya que esto permite verificar si una solución realmente funciona.

Finalmente, la implementación de imágenes y filtros por categorías permitió mejorar la presentación y funcionalidad de GameCatalog, cumpliendo con los objetivos del taller.