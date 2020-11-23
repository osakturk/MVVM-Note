package com.osakturk.notetask.util

sealed class DataHolder<out T> {

    data class Success<out T>(val data: T, val isRemote: Boolean) : DataHolder<T>()

    data class Error<out T>(val data: T) : DataHolder<T>()
}