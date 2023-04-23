package com.miu.movieapp.other

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

/**
 * Returns a [ViewModelProvider.Factory] which will return the result of [create] when it's
 * [ViewModelProvider.Factory.create] function is called.
 *
 */
inline fun <reified VM : ViewModel> viewModelProviderFactoryOf(
    crossinline create: () -> VM
): ViewModelProvider.Factory = viewModelFactory {
    initializer {
        create.invoke()
    }
}
