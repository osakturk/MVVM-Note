package com.osakturk.notetask.di.module

import com.osakturk.notetask.di.scope.AppScope
import com.osakturk.notetask.network.AbstractApiUtils
import com.osakturk.notetask.repository.NotesRepository
import dagger.Module
import dagger.Provides

@Module(includes = [ApiUtilsModule::class])
class RepositoryModule {

    @AppScope
    @Provides
    fun provideNotesRepository(apiUtils: AbstractApiUtils): NotesRepository {
        return NotesRepository(apiUtils)
    }

}