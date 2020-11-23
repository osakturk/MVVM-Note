package com.osakturk.notetask.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * @author osakturk
 */

open class BaseViewModel: ViewModel() {

    protected val disposableList = CompositeDisposable()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        disposableList.clear()
        super.onCleared()
    }
}