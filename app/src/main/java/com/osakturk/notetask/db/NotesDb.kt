package com.osakturk.notetask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.osakturk.notetask.model.Note
import com.osakturk.notetask.model.converters.Converters

@Database(
    entities = [
        Note::class],
    version = 8,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NotesDb : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}