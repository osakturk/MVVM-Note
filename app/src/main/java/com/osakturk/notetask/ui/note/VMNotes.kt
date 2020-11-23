package com.osakturk.notetask.ui.note

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.osakturk.notetask.base.BaseViewModel
import com.osakturk.notetask.db.NotesDao
import com.osakturk.notetask.model.Note
import kotlinx.coroutines.launch
import javax.inject.Inject

class VMNotes @Inject constructor(
    private val notesDao: NotesDao
) : BaseViewModel() {

    val notesResponse = MutableLiveData<List<Note>>()
    val deleteResponse = MutableLiveData<Boolean>()

    fun getNotesFromDb() {
        viewModelScope.launch {
            val response = notesDao.getAllNotes()
            response.let {
                notesResponse.postValue(it)
            }
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch {
            notesDao.deleteAll()
            deleteResponse.postValue(true)
        }
    }
}