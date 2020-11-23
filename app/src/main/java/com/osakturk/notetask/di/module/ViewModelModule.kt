package com.osakturk.notetask.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.osakturk.notetask.di.ViewModelKey
import com.osakturk.notetask.ui.note.VMEditAndCreateNote
import com.osakturk.notetask.ui.note.VMNotes
import com.osakturk.notetask.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(VMNotes::class)
    abstract fun bindNotesViewModel(vmNotes: VMNotes): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VMEditAndCreateNote::class)
    abstract fun bindEditAndCreateNoteViewModel(vmNotes: VMEditAndCreateNote): ViewModel
}