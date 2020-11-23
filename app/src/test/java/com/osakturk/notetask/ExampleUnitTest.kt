package com.osakturk.notetask

import androidx.room.Room
import com.osakturk.notetask.db.NotesDao
import com.osakturk.notetask.db.NotesDb
import com.osakturk.notetask.model.Note
import org.junit.Test

import org.junit.Assert.*
import com.osakturk.notetask.network.ApiService
import com.osakturk.notetask.network.RequestInterceptor
import com.osakturk.notetask.util.ApiConstants
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private var apiService: ApiService? = null

    @Before
    fun createService() {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.addInterceptor(RequestInterceptor())
        okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        apiService = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
            .create(ApiService::class.java)
    }


    @Test
    fun getNotesData() {
        try {
            val response = apiService?.loadNotesData("https://samples.openweathermap.org/","London,uk","b6907d289e10d714a6e88b30761fae22")
            assertEquals(response?.title, "")
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
