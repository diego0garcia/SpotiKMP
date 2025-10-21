package org.example.project.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.utils.Cancion
import spotikmp.composeapp.generated.resources.Res
import spotikmp.composeapp.generated.resources.recycle_bin
import org.jetbrains.compose.resources.painterResource


@Composable
fun TarjetaCancion(
    cancion: Cancion,
    onBorar: (Cancion) -> Unit,
    onPlay: (Cancion) -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        //elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (cancion.activo)
                MaterialTheme.colorScheme.background
            else
                MaterialTheme.colorScheme.primary
        ),
        onClick = {
            onPlay(cancion)
        }

    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(
                //top = 6.dp,
                //bottom = 6.dp
            )
        ){
            Image(
                painter = painterResource(cancion.imagen),
                contentDescription = cancion.nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp).size(100.dp).padding(end = 12.dp, top = 12.dp).clip(RoundedCornerShape(28.dp))
            )

            Text(
                cancion.nombre,
                fontWeight = FontWeight.Bold
            )

            Text(
                cancion.asrtista
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                //modifier = Modifier.
            ) {
                Button(
                    onClick = {onBorar(cancion)}
                ){
                    Icon(
                        painter = painterResource(Res.drawable.recycle_bin),
                        contentDescription = "Delete",
                        tint = Color.Red,
                        modifier = Modifier.size(26.dp),
                    )
                }
            }
        }
    }
}
