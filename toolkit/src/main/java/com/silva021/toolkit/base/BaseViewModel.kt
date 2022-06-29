package com.silva021.toolkit.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<VT, VA> : ViewModel() {
    abstract val viewState: MutableLiveData<VT>
    abstract fun dispatchViewAction(viewAction: VA)
}