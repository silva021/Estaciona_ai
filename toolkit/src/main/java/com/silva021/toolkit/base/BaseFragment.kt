package com.silva021.toolkit.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment<out T : BaseViewModel<*, *>>(
    viewModelClass: KClass<T>,
) : Fragment() {
    protected open val viewModel: T by viewModel(clazz = viewModelClass, state = { Bundle() })
}