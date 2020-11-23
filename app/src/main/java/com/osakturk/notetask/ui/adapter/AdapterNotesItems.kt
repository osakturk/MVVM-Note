package com.osakturk.notetask.ui.adapter

import com.osakturk.notetask.R
import com.osakturk.notetask.binding.DataBoundAdapter
import com.osakturk.notetask.binding.DataBoundViewHolder
import com.osakturk.notetask.databinding.ListItemBinding
import com.osakturk.notetask.interfaces.IUpdateNoteItemClick
import com.osakturk.notetask.model.Note
import com.osakturk.notetask.util.convertLongToTime

class AdapterNotesItems() : DataBoundAdapter<ListItemBinding>(R.layout.list_item) {



    override fun bindItem(
        holder: DataBoundViewHolder<ListItemBinding>,
        position: Int,
        payloads: List<Any>
    ) {
        holder.binding.data = noteItemList[position]
        holder.binding.callback = iUpdateNoteItemClick
        holder.binding.createDate.text = convertLongToTime(noteItemList[position].createDate)
        holder.binding.editButton.setOnClickListener {
            iUpdateNoteItemClick.onEditNoteClick(noteItemList[position])
        }
    }

    override fun getItemCount(): Int {
        return noteItemList.size
    }

    private lateinit var noteItemList: ArrayList<Note>
    private lateinit var iUpdateNoteItemClick: IUpdateNoteItemClick

    constructor(
        noteList: ArrayList<Note>,
        iEditNoteClick: IUpdateNoteItemClick
    ) : this() {
        this.noteItemList = noteList
        this.iUpdateNoteItemClick = iEditNoteClick
    }

    fun getItem(position: Int): Note{
        return noteItemList[position]
    }
}