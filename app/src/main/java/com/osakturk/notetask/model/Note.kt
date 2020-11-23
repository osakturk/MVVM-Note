package com.osakturk.notetask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "notes")
class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id:Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val desc: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("createDate")
    val createDate: Long,
    @SerializedName("isEdited")
    val isEdited: Boolean
)