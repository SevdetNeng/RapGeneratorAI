package com.sevdetneng.rapgeneratorai.util

object Extensions {
    fun String.getVerses() : List<String> {
        return this.trim().split("\n\n")
    }
}
