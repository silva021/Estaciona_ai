package com.silva021.estacionaa.ui.exit.payment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.silva021.estacionaa.data.api.ApiResponse
import com.silva021.toolkit.base.BaseViewModel
import com.silva021.estacionaa.domain.usecase.PaymentExitVehicleUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PaymentDialogViewModel(
    val paymentExitVehicle: PaymentExitVehicleUseCase,
) : BaseViewModel<ViewState, ViewAction>() {
    override val viewState = MutableLiveData<ViewState>()

    override fun dispatchViewAction(viewAction: ViewAction) {
        when (viewAction) {
            is ViewAction.ConfirmPayment -> handleConfirmPayment(viewAction.plate)
        }
    }

    private fun handleConfirmPayment(plate: String) {
        viewModelScope.launch {
            viewState.value = ViewState.LoadingScreen
            when (val value = paymentExitVehicle(plate)) {
                is ApiResponse.Success -> handleSuccess(value.data)
                is ApiResponse.Error -> viewState.value = ViewState.Error
            }
            delay(1000)
            viewState.postValue(ViewState.DismissScreen)
        }
    }

    private fun handleSuccess(isSuccess: Boolean) {
        if (isSuccess) {
            viewState.value = ViewState.SuccessScreen
        } else {
            viewState.value = ViewState.Error
        }
    }
}