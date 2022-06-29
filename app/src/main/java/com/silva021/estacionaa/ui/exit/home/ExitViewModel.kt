package com.silva021.estacionaa.ui.exit.home

import androidx.lifecycle.MutableLiveData
import com.silva021.toolkit.base.BaseViewModel
import com.silva021.estacionaa.domain.usecase.ValidateTransitBoardUseCase

class ExitViewModel(
    val validateTransitBoard: ValidateTransitBoardUseCase,
) : BaseViewModel<ViewState, ViewAction>() {
    override val viewState = MutableLiveData<ViewState>()

    override fun dispatchViewAction(viewAction: ViewAction) {
        when (viewAction) {
            is ViewAction.ValidateBoard -> handleValidateValue(viewAction.value)
        }
    }

    private fun handleValidateValue(value: String) {
        viewState.value = ViewState.EnableButton(validateTransitBoard(value))
    }
}