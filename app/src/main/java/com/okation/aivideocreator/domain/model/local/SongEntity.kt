package com.okation.aivideocreator.domain.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Song", indices = [Index(value = ["url"], unique = true)])
data class SongEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "url")
    val songUrl : String,
    val rapperName : String,
    val rapperImgId : Int,
    val songTitle : String
)