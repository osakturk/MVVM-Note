package com.osakturk.notetask.interfaces

import com.osakturk.notetask.model.Note

interface IUpdateNoteItemClick {
    fun onEditNoteClick(note: Note)
}