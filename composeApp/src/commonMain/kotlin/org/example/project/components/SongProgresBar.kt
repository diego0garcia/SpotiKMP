package org.example.project.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.example.project.utils.Cancion

@Composable
fun SongProgresBar(cancion: Cancion, playing: Boolean) {
    var currentProgress by remember { mutableFloatStateOf(0.00f) }

    LaunchedEffect ( playing){
        if (playing){
            while (playing and (currentProgress < cancion.duracion)){
                delay(1000)
                currentProgress = currentProgress + 0.01f
            }
        }
    }

    LaunchedEffect(cancion){
        currentProgress = 0.00f
    }

    Column (
        modifier = Modifier.width(200.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("${(currentProgress * 100).toInt() / 100.00f}")
            Text(cancion.duracion.toString() + "s")
        }
        LinearProgressIndicator(
            progress = { currentProgress / cancion.duracion.toFloat() },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

