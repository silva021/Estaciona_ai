package com.silva021.estacionaa.ui.entrance

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.silva021.toolkit.base.BaseViewModel
import com.silva021.estacionaa.data.api.ApiResponse
import com.silva021.estacionaa.domain.usecase.RegisterEntranceVehicleUseCase
import com.silva021.estacionaa.domain.usecase.ValidateTransitBoardUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EntranceViewModel(
    val validateTransitBoard: ValidateTransitBoardUseCase,
    val registerEntranceVehicleUseCase: RegisterEntranceVehicleUseCase,
) : BaseViewModel<ViewState, ViewAction>() {
    override val viewState = MutableLiveData<ViewState>()

    override fun dispatchViewAction(viewAction: ViewAction) {
        when (viewAction) {
            is ViewAction.ValidateBoard -> handleValidateValue(viewAction.value)
            is ViewAction.ClickConfirmEntrance -> handleClickConfirmEntrance(viewAction.board)
        }
    }

    private fun handleClickConfirmEntrance(board: String) {
        viewModelScope.launch {
            viewState.value = ViewState.Loading
            when (val value = registerEntranceVehicleUseCase(board)) {
                is ApiResponse.Error -> handleError()
                is ApiResponse.Success -> handleSuccess()
            }
            delay(1000)
            viewState.value = ViewState.SetupScreen
        }
    }

    private fun handleSuccess() {
        viewState.value = ViewState.LoadingSuccess
    }

    private fun handleError() {
        viewState.value = ViewState.ErrorScreen
    }

    private fun handleValidateValue(value: String) {
        viewState.value = ViewState.EnableButton(validateTransitBoard(value))
    }
}