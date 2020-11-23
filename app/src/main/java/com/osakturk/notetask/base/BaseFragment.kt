package com.osakturk.notetask.base

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.osakturk.notetask.BR
import com.osakturk.notetask.di.Injectable
import com.osakturk.notetask.interfaces.IDecisionDialog
import com.osakturk.notetask.ui.note.EditAndCreateNoteFragment
import javax.inject.Inject


/**
 * @author osakturk
 */

abstract class BaseFragment<VM : ViewModel, DB : ViewDataBinding> : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: VM

    protected lateinit var binding: DB

    abstract val getLayoutId: Int

    abstract val viewModelClass: Class<VM>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        binding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    fun showInfoDialog(stringRes: Int) {
        context?.let {
            AlertDialog.Builder(it)
                .setMessage(stringRes)
                .setNeutralButton(android.R.string.ok) { view, _ -> view.dismiss() }
                .show()
        }
    }

    fun showDecisionDialog(stringRes: Int, iDecisionDialog: IDecisionDialog) {
        context?.let {
            AlertDialog.Builder(it)
                .setMessage(stringRes)
                .setPositiveButton(android.R.string.yes) { view, _ ->
                    iDecisionDialog.onPositiveCallback()
                    view.dismiss()
                }
                .setNegativeButton(android.R.string.no){view, _ ->
                    iDecisionDialog.onNegativeCallback()
                    view.dismiss()
                }
                .show()
        }
    }

    fun pushFragment(bundle: Bundle, targetFragment: Fragment){
        val fragment = targetFragment
        fragment.arguments = bundle
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.setCustomAnimations(
            com.osakturk.notetask.R.anim.nav_default_enter_anim,
            com.osakturk.notetask.R.anim.nav_default_exit_anim, com.osakturk.notetask.R.anim.nav_default_pop_enter_anim, com.osakturk.notetask.R.anim.nav_default_pop_exit_anim)
        ft.replace(com.osakturk.notetask.R.id.fragment, fragment)
        ft.addToBackStack("notes")
        ft.commit()
    }

    fun popFragment(){
        fragmentManager!!.popBackStack()
    }

}