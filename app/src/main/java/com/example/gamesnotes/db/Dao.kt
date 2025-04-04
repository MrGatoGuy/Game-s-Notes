package com.example.gamesnotes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gamesnotes.entity.Note
import com.example.gamesnotes.entity.Title

@Dao
interface Dao {
    //Title
    @Insert
    fun insertTitle(title: Title)

    @Query("SELECT * FROM title")
    fun getAllTitles(): List<Title>

    @Query("SELECT * FROM title WHERE id = :id")
    fun getTitle(id: Int): Title

    @Query("UPDATE title SET name = :name, color = :color, text_color = :textColor WHERE id = :id")
    fun editTitle(name: String, color: Int, textColor: Int, id: Int)

    @Query("DELETE FROM title WHERE id = :id")
    fun deleteTitle(id: Int)

    //Note
    @Insert
    fun insertNote(note: Note)

    @Query("SELECT * FROM note WHERE id_title = :idTitle")
    fun getAllNotes(idTitle: String): List<Note>

//    @Query("SELECT * FROM note WHERE id = :id")
//    fun getAllNotesWithId(id: String): List<Note>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNote(id: Int): Note

    @Query("UPDATE note SET name = :name, bottom_color = :bottomColor, color = :color, text_color = :textColor, description = :description," +
            " type = :type,t_img = :tImg, id_title = :idTitle, time = :time, points = :points WHERE id = :id")
    fun editNote(points: String,time: String,description: String,idTitle: String,name: String,type: Int,tImg: Int,bottomColor: Int,
                 color: Int, textColor: Int, id: Int)

    @Query("DELETE FROM note WHERE id = :id")
    fun deleteNote(id: Int)

    @Query("DELETE FROM note WHERE id_title = :idTitle")
    fun deleteAllNotes(idTitle: String)

    @Query("SELECT rotate FROM note WHERE id = :id")
    fun getRotateNote(id: Int): Boolean

    @Query("UPDATE note SET rotate = :bool WHERE id = :id")
    fun rotateNote(id: Int, bool: Boolean)
}