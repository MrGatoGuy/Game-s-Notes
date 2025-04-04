package com.example.gamesnotes.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "title")
data class Title(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "color")
    var color: Int,
    @ColumnInfo(name = "text_color")
    var textColor: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int?
): Serializable
