package com.okation.aivideocreator.util

import com.okation.aivideocreator.domain.model.local.Rapper
import com.okation.aivideocreator.domain.model.local.Song
import com.okation.aivideocreator.domain.model.local.SongEntity

object Extensions {
    fun String.getVerses() : ArrayList<ArrayList<String>> {
        val verses = this.trim().split("\n\n")
        val lyrics : ArrayList<ArrayList<String>> = arrayListOf()
        verses.forEach {
            val innerArray : ArrayList<String> = arrayListOf()
            it.lines().forEach { lines ->
                innerArray.add(lines)
            }
            lyrics.add(innerArray)
        }
        return lyrics
    }

    fun Int.millisecondsToMinsAndSeconds() : String {
        val totalSeconds = this/1000
        val minutes = totalSeconds/60
        val seconds = totalSeconds % 60
        if(seconds<10){
            return "$minutes:0$seconds"
        }
        return "$minutes:$seconds"
    }

    fun Song.songToSongEntity() : SongEntity{
        return SongEntity(
            songUrl = this.songUrl,
            songTitle = this.songTitle,
            rapperName = this.rapper.rapperName!!,
            rapperImgId = this.rapper.rapperImg!!,
        )
    }

    fun SongEntity.songEntityToSong() : Song {
        return Song(
            songUrl = this.songUrl,
            rapper = Rapper(
                rapperImg = this.rapperImgId,
                rapperName = this.rapperName
            ),
            songTitle = this.songTitle
        )
    }
}
