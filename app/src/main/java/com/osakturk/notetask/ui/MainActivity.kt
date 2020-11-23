package com.osakturk.notetask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.osakturk.notetask.R
import com.osakturk.notetask.ui.note.NotesFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, NotesFragment())
        ft.commit()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
