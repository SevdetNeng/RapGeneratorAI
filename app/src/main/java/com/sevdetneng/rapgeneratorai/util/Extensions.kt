package com.sevdetneng.rapgeneratorai.util

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
}
