package com.osakturk.notetask.di.module

import com.osakturk.notetask.ui.note.EditAndCreateNoteFragment
import com.osakturk.notetask.ui.note.NotesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author osakturk
 */

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeNotesFragment(): NotesFragment

    @ContributesAndroidInjector
    abstract fun contributeEditAndCreateNoteFragment(): EditAndCreateNoteFragment
}