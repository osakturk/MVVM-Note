package com.osakturk.notetask.ui.note

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.osakturk.notetask.base.BaseViewModel
import com.osakturk.notetask.db.NotesDao
import com.osakturk.notetask.model.Note
import com.osakturk.notetask.repository.NotesMockRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class VMEditAndCreateNote @Inject constructor(
    private val notesMockRepository: NotesMockRepository,
    private val notesDao: NotesDao
) : BaseViewModel() {

    val insertNoteResponse = MutableLiveData<Boolean>()
    val getModelResponse = MutableLiveData<Note>()
    val deleteNoteResponse = MutableLiveData<Boolean>()

    fun insertDataToDb(it: Note?) {
        viewModelScope.launch {
            it?.let { model ->
                val response = notesDao.insertNote(model)
                response.let {
                    if (it>0){
                        insertNoteResponse.postValue(true)
                    }
                }
            }
        }
    }

    fun updateDataToDb(it: Note?) {
        viewModelScope.launch {
            it?.let { model ->
                val response = notesDao.updateNote(model)
                response.let {
                    if (it>0){
                        insertNoteResponse.postValue(true)
                    }
                }
            }
        }
    }

    fun deleteFromDb(it: Note?) {
        viewModelScope.launch {
            it?.let { model ->
                val response = notesDao.deleteNote(model)
                response.let {
                    if (it >0){
                        deleteNoteResponse.postValue(true)
                    }
                }

            }
        }
    }

    fun getNoteModel(it: Long?) {
        viewModelScope.launch {
            it?.let { model ->
                val response = notesDao.getNoteModel(model)
                response.let {
                    getModelResponse.postValue(it)
                }
            }
        }
    }
}