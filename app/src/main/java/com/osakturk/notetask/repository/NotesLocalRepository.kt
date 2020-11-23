package com.osakturk.notetask.repository

import com.osakturk.notetask.db.NotesDao
import javax.inject.Inject

class NotesLocalRepository @Inject constructor(private val notesDao: NotesDao){
}