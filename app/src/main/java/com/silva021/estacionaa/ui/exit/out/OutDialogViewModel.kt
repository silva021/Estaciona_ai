package com.silva021.estacionaa.ui.exit.out

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.silva021.estacionaa.data.api.ApiResponse
import com.silva021.toolkit.base.BaseViewModel
import com.silva021.estacionaa.domain.usecase.ConfirmOutVehicleUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OutDialogViewModel(
    val confirmOutVehicle: ConfirmOutVehicleUseCase,
) : BaseViewModel<ViewState, ViewAction>() {
    override val viewState = MutableLiveData<ViewState>()

    override fun dispatchViewAction(viewAction: ViewAction) {
        when (viewAction) {
            is ViewAction.ConfirmOut -> handleConfirmOut(viewAction.plate)
        }
    }

    private fun handleConfirmOut(plate: String) {
        viewModelScope.launch {
            viewState.value = ViewState.LoadingScreen
            when (val value = confirmOutVehicle(plate)) {
                is ApiResponse.Success -> handleSuccess(value.data)
                is ApiResponse.Error -> viewState.value = ViewState.ErrorScreen
            }
            delay(2000)
            viewState.value = ViewState.DismissScreen

        }
    }

    private fun handleSuccess(isOutSuccess: Boolean) {
        if (isOutSuccess)
            viewState.value = ViewState.SuccessScreen
        else
            viewState.value = ViewState.ErrorScreen
    }
}