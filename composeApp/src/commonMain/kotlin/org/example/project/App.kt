package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources._2k16
import kotlinproject.composeapp.generated.resources.hqdd
import kotlinproject.composeapp.generated.resources.next_1
import kotlinproject.composeapp.generated.resources.play
import kotlinproject.composeapp.generated.resources.previous_1
import kotlinproject.composeapp.generated.resources.sauce_boyz
import kotlinproject.composeapp.generated.resources.scat_pack
import kotlinproject.composeapp.generated.resources.stop
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {

    var selected_song by remember { mutableStateOf<Cancion?>(null) }

    var play_song by remember { mutableStateOf(true) }

    var song_list: MutableList<Cancion> = remember {mutableStateListOf(
        Cancion("Hasta Que Dios Diga",3.15,Res.drawable.hqdd),
        Cancion("3 am",2.32,Res.drawable.sauce_boyz),
        Cancion("Scat Pack",3.02,Res.drawable.scat_pack),
        Cancion("2K16",2.58,Res.drawable._2k16)
    )}

    fun delete(cancion: Cancion){
       song_list.remove(cancion)
    }

    fun play(cancion: Cancion){
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
                ) {
                    //Contenedor con los botones de accion
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.Top,
                    ) {
                        //Previus
                        Button(
                            onClick = {

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
                                painter = painterResource(if (play_song){Res.drawable.play}else{Res.drawable.stop}),
                                contentDescription = "Play",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        //Next
                        Button(
                            onClick = {

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
        ) { paddingValues ->
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(34.dp),
                verticalItemSpacing = 34.dp,
                modifier = Modifier.fillMaxWidth().padding(paddingValues).padding(34.dp),
            ){
                items(song_list.size){
                    TarjetaCancion(
                        song_list[it],
                        {delete(it)},
                        { play(it)}
                    )
                }
            }
        }
    }
}

