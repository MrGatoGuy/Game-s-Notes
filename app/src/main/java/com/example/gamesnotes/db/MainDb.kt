package com.example.gamesnotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gamesnotes.entity.Note
import com.example.gamesnotes.entity.Title

@Database(entities = [Title::class, Note::class], version = 1)
abstract class MainDb: RoomDatabase() {

    abstract fun getDao(): Dao

    companion object{
        fun getDb(context: Context): MainDb {
            return Room.databaseBuilder(
                context.applicationContext, MainDb::class.java,
                "gamesnotes.db"
            ).build()
        }
    }
}