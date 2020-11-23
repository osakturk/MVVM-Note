package com.osakturk.notetask.ui.note

import android.os.Bundle
import androidx.lifecycle.Observer
import com.osakturk.notetask.R
import com.osakturk.notetask.base.BaseFragment
import com.osakturk.notetask.databinding.FragmentNotesBinding
import com.osakturk.notetask.di.Injectable
import com.osakturk.notetask.interfaces.IUpdateNoteItemClick
import com.osakturk.notetask.model.Note
import com.osakturk.notetask.ui.adapter.AdapterNotesItems

class NotesFragment : BaseFragment<VMNotes, FragmentNotesBinding>(), Injectable {
    override val getLayoutId: Int = R.layout.fragment_notes
    override val viewModelClass = VMNotes::class.java
    var noteList: ArrayList<Note> = arrayListOf()

    private var adapterNotesItems = AdapterNotesItems(noteList, object : IUpdateNoteItemClick {
            override fun onEditNoteClick(note: Note){
                val bundle = Bundle()
                bundle.putLong("id",note.id)
                bundle.putString("title",note.title)
                bundle.putString("description",note.desc)
                bundle.putString("imageUrl",note.imageUrl)
                pushFragment(bundle, EditAndCreateNoteFragment())
            }
        })

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeData()
        viewModel.getNotesFromDb()
        binding.noteAdapter.adapter = adapterNotesItems
        binding.noteAdapter.setHasFixedSize(true)
    }

    private fun subscribeData() {
        viewModel.notesResponse.observe(this, Observer {
            if (it != null){
                noteList.clear()
                noteList.addAll(it)
                adapterNotesItems.notifyDataSetChanged()
            }
        })
        viewModel.deleteResponse.observe(this, Observer {
            if (it){
                viewModel.getNotesFromDb()
            }
        })
        binding.newNote.setOnClickListener {
            pushFragment(Bundle(),EditAndCreateNoteFragment())
        }
        binding.deleteButton.setOnClickListener {
            viewModel.deleteAllNotes()
        }
    }
}