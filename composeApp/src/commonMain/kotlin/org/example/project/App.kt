package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.components.PlaySong
import org.example.project.components.SongProgresBar
import org.example.project.components.TarjetaCancion
import org.example.project.utils.Cancion
import spotikmp.composeapp.generated.resources.Res
import spotikmp.composeapp.generated.resources._2k16
import spotikmp.composeapp.generated.resources.hqdd
import spotikmp.composeapp.generated.resources.next_1
import spotikmp.composeapp.generated.resources.play
import spotikmp.composeapp.generated.resources.previous_1
import spotikmp.composeapp.generated.resources.sauce_boyz
import spotikmp.composeapp.generated.resources.scat_pack
import spotikmp.composeapp.generated.resources.stop
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {

    var selected_song by remember { mutableStateOf<Cancion?>(null) }
    var play_song by remember { mutableStateOf(false) }

    var song_list: MutableList<Cancion> = remember {mutableStateListOf(
        Cancion("Hasta Que Dios Diga",3.15,Res.drawable.hqdd, "Anuel AA",play_song),
        Cancion("3 am",2.32,Res.drawable.sauce_boyz, "Eladio Carrión",play_song),
        Cancion("Scat Pack",3.02,Res.drawable.scat_pack, "Clarent",play_song),
        Cancion("2K16",2.58,Res.drawable._2k16, "Omar Courtz",play_song)
    )}

    fun delete(cancion: Cancion){
        song_list.remove(cancion)
        selected_song = null
    }

    fun play(cancion: Cancion){
        play_song = true
        selected_song = cancion
    }

    //Componente
    MaterialTheme {
        Scaffold(
            //Barra superior
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    modifier = Modifier.height(120.dp),
                    title = {
                        PlaySong(
                            selected_song,
                        )
                    }
                )
            },
            //Barra inferior
            bottomBar = {
                BottomAppBar (
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.height(100.dp)
                ) {
                    Column (
                        modifier = Modifier.fillMaxWidth().fillMaxWidth()
                    ){
                        //Contenedor de la barra de reproduccion
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            ){
                            if (selected_song != null){
                                SongProgresBar(selected_song!!, play_song)
                            }
                        }
                        //Contenedor con los botones de accion
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.Top,
                        ) {
                            //Previus
                            Button(
                                onClick = {
                                    val pos = song_list.lastIndexOf(selected_song)
                                    if (pos == 0){selected_song = song_list.get(song_list.size - 1)}else{selected_song = song_list[pos - 1]}
                                }
                            ){
                                Icon(
                                    painter = painterResource(Res.drawable.previous_1),
                                    contentDescription = "Play",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            //Play
                            Button(
                                onClick = {
                                    play_song = !play_song
                                }
                            ){
                                Icon(
                                    painter = painterResource(if (!play_song){Res.drawable.play}else{Res.drawable.stop}),
                                    contentDescription = "Play",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            //Next
                            Button(
                                onClick = {
                                    val pos = song_list.lastIndexOf(selected_song)
                                    if (pos == song_list.size -1){selected_song = song_list.get(0)}else{selected_song = song_list[pos + 1]}
                                }
                            ){
                                Icon(
                                    painter = painterResource(Res.drawable.next_1),
                                    contentDescription = "Play",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }

            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text("Tu lista de reproduccion", color = Color.Black, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(34.dp),
                    verticalItemSpacing = 34.dp,
                    modifier = Modifier.fillMaxWidth().padding(paddingValues).padding(34.dp).weight(1f),
                ){
                    items(song_list.size){
                        TarjetaCancion(
                            song_list[it],
                            { delete(it) },
                            { play(it) }
                        )
                    }
                }
            }
        }
    }
}

