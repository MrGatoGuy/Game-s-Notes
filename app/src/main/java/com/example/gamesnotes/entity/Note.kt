package com.example.gamesnotes.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note")
data class Note(
    @ColumnInfo(name = "points")
    var points: String,
    @ColumnInfo(name = "time")
    var time: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "id_title")
    var idTitle: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "type")
    var type: Int,
    @ColumnInfo(name = "t_img")
    var tImg: Int,
    @ColumnInfo(name = "color")
    var color: Int,
    @ColumnInfo(name = "text_color")
    var textColor: Int,
    @ColumnInfo(name = "text_color_hint")
    var textColorHint: Int,
    @ColumnInfo(name = "bottom_color")
    var bottomColor: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "rotate")
    var rotate: Boolean = false
): Serializable
