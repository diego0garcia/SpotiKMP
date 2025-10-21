package org.example.project.components

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.viewmodels.MainViewModel
import org.example.project.viewmodels.SoundProgressBarViewModel
import org.jetbrains.compose.resources.painterResource
import spotikmp.composeapp.generated.resources.Res
import spotikmp.composeapp.generated.resources.next_1
import spotikmp.composeapp.generated.resources.play
import spotikmp.composeapp.generated.resources.previous_1
import spotikmp.composeapp.generated.resources.stop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(viewModel: MainViewModel){
    //Variables
    var selected_song = viewModel.selected_song
    var play_song = viewModel.play_song
    var song_list = viewModel.song_list
    var soundProgressBarViewModel = remember { SoundProgressBarViewModel() }

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
                                SongProgressBar(selected_song!!, play_song, soundProgressBarViewModel)
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
                                    viewModel.previusSong()
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
                                    viewModel.togglePlay()
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
                                    viewModel.nextSong()
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
                            { viewModel.delete(it) },
                            { viewModel.play(it) }
                        )
                    }
                }
            }
        }
    }
}