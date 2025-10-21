package org.example.project.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.project.utils.Song
import spotikmp.composeapp.generated.resources.Res
import spotikmp.composeapp.generated.resources._2k16
import spotikmp.composeapp.generated.resources.hqdd
import spotikmp.composeapp.generated.resources.sauce_boyz
import spotikmp.composeapp.generated.resources.scat_pack

class MainViewModel {
    var selected_song by mutableStateOf<Song?>(null)
    var play_song by mutableStateOf(false)

    var song_list: MutableList<Song> =  mutableStateListOf(
        Song("Hasta Que Dios Diga",3.15,Res.drawable.hqdd, "Anuel AA",play_song),
        Song("3 am",2.32,Res.drawable.sauce_boyz, "Eladio Carrión",play_song),
        Song("Scat Pack",3.02,Res.drawable.scat_pack, "Clarent",play_song),
        Song("2K16",2.58,Res.drawable._2k16, "Omar Courtz",play_song)
    )

    fun delete(song: Song){
        song_list.remove(song)
        selected_song = null
    }

    fun play(song: Song){
        play_song = true
        selected_song = song
    }

    fun togglePlay(){
        play_song = !play_song
    }

    fun previusSong(){
        val pos = song_list.lastIndexOf(selected_song)
        if (pos == 0){selected_song = song_list.get(song_list.size - 1)}else{selected_song = song_list[pos - 1]}
    }

    fun nextSong(){
        val pos = song_list.lastIndexOf(selected_song)
        if (pos == song_list.size -1){selected_song = song_list.get(0)}else{selected_song = song_list[pos + 1]}
    }

}