package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.dafault_song
import org.jetbrains.compose.resources.painterResource

@Composable
fun PlaySong(
    selectedSong: Cancion?
){
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,

    ){
        Image(
            painter = painterResource(selectedSong?.imagen?: Res.drawable.dafault_song),
            contentDescription = selectedSong?.nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp).size(100.dp).padding(end = 12.dp, top = 12.dp).clip(RoundedCornerShape(28.dp))
        )

        Text(
            selectedSong?.nombre?: "Selecciona una cancion",
            fontWeight = FontWeight.Bold
        )
    }
}