package com.osakturk.notetask.di.module

import com.osakturk.notetask.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author osakturk
 */

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}