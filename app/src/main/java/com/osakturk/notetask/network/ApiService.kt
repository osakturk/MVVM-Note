package com.osakturk.notetask.network

import com.osakturk.notetask.model.Note
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


//Eğer Mock yerine API kullanacaksak buraya servisleri yazmamız gerek
interface ApiService {

    @GET("data/notes")
    fun loadNotesData(@Url url: String, @Query("q") location: String, @Query("appid") appId: String): Note
}