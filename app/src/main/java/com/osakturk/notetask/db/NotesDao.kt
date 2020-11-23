package com.osakturk.notetask.db

import androidx.room.*
import com.osakturk.notetask.model.Note

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note): Long

    @Update
    suspend fun updateNote(note: Note): Int

    @Delete
    suspend fun deleteNote(note: Note): Int

    @Query("DELETE FROM notes")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM notes WHERE id=:id")
    suspend fun getNoteModel(id: Long): Note

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Note>
}