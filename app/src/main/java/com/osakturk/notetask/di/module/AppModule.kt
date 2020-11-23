package com.osakturk.notetask.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.osakturk.notetask.db.NotesDao
import com.osakturk.notetask.db.NotesDb
import com.osakturk.notetask.di.qualifiers.BaseUrlQualifier
import com.osakturk.notetask.di.scope.AppScope
import dagger.Module
import dagger.Provides

/**
 * @author osakturk
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @AppScope
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @AppScope
    @BaseUrlQualifier
    fun provideBaseUrl(): String {
        return "http://www.google.com/"
    }

    @AppScope
    @Provides
    fun provideNotesDb(app: Application): NotesDb {
        return Room
            .databaseBuilder(app, NotesDb::class.java, "notes.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @AppScope
    @Provides
    fun provideNotesDao(db: NotesDb): NotesDao {
        return db.notesDao()
    }
}