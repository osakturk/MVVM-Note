package com.osakturk.notetask.model.converters

import androidx.room.TypeConverter
import com.osakturk.notetask.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromStringNoteList(value: String): List<Note> {
        val type = object: TypeToken<List<Note>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromArrayList(list: List<Note>): String {
        val type = object: TypeToken<List<Note>>() {}.type
        return Gson().toJson(list, type)
    }
}