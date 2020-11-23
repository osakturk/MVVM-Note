package com.osakturk.notetask

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.osakturk.notetask.db.NotesDao
import com.osakturk.notetask.db.NotesDb
import com.osakturk.notetask.model.Note
import kotlinx.android.synthetic.main.list_item.view.*
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import java.util.*
import kotlin.random.Random

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.osakturk.notetask", appContext.packageName)
    }


    private var noteDAO: NotesDao? = null
    private var db: NotesDb? = null

    @Before
    fun onCreateDB() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, NotesDb::class.java).build()
        noteDAO = db!!.notesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db!!.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun readAndWriteTests() {
        val note = Note(
            Random.nextLong(0, 100000),
            "Title Test",
            "Desc test",
            "https://raw.githubusercontent.com/osakturk/MVVM-Note/master/screenshots/Screenshot_1606126301.png",
            Date().time,
            isEdited = false
        )

        // insert
        val insertedID = noteDAO!!.insertNote(note)
        assertNotNull(insertedID)

        // find by id
        val inserted = noteDAO!!.getNoteModel(insertedID)
        assertNotNull(inserted)
        assertTrue(inserted.title == note.title)

        //update
        val updatedQtd = noteDAO!!.updateNote(inserted)
        assertEquals(updatedQtd.toLong(), 1)

        // delete
        val deletedQtd = noteDAO!!.deleteNote(inserted)
        assertEquals(deletedQtd.toLong(), 1)
    }

    @Test
    @Throws(Exception::class)
    suspend fun findLists() {
        val note = Note(
            Random.nextLong(0, 100000),
            "Title Test",
            "Desc test",
            "https://raw.githubusercontent.com/osakturk/MVVM-Note/master/screenshots/Screenshot_1606126301.png",
            Date().time,
            isEdited = false
        )

        // bulk insert
        val ids = noteDAO!!.insertNote(note)
        val note2 = Note(
            Random.nextLong(0, 100000),
            "Title Test",
            "Desc test",
            "https://raw.githubusercontent.com/osakturk/MVVM-Note/master/screenshots/Screenshot_1606126301.png",
            Date().time,
            isEdited = false
        )
        val ids2 = noteDAO!!.insertNote(note2)
        assertNotNull(ids)
        assertNotNull(ids2)

        // find all resumed notes with done status
        val doneNotes = noteDAO!!.getAllNotes()
        assertNotNull(doneNotes)

    }

}
