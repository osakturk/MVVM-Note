package com.osakturk.notetask.model.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.osakturk.notetask.model.Note

class ListConverter {
    @TypeConverter
    fun fromString(value: String): List<Note> {
        val type = object: TypeToken<List<Note>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromArrayList(list: List<Note>): String {
        val type = object: TypeToken<List<Note>>() {}.type
        return Gson().toJson(list, type)
    }
}