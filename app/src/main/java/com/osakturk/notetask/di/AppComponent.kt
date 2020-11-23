package com.osakturk.notetask.di

import android.app.Application
import com.osakturk.notetask.NotesApplication
import com.osakturk.notetask.di.module.ActivityModule
import com.osakturk.notetask.di.module.AppModule
import com.osakturk.notetask.di.module.RepositoryModule
import com.osakturk.notetask.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

/**
 * @author osakturk
 */

@AppScope
@Component(
        modules = [
            AndroidInjectionModule::class,
            AndroidSupportInjectionModule::class, //added for X
            AppModule::class,
            RepositoryModule::class,
            ActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(notesApplication: NotesApplication)
}