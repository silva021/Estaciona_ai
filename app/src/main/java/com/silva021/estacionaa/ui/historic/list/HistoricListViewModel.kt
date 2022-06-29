package com.silva021.estacionaa.ui.historic.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.silva021.estacionaa.data.api.ApiResponse
import com.silva021.toolkit.base.BaseViewModel
import com.silva021.estacionaa.domain.model.HistoricModel
import com.silva021.estacionaa.domain.usecase.GetHistoricVehicleUseCase
import kotlinx.coroutines.launch

class HistoricListViewModel(
    val getHistoric: GetHistoricVehicleUseCase,
) : BaseViewModel<ViewState, ViewAction>() {
    override val viewState = MutableLiveData<ViewState>()

    override fun dispatchViewAction(viewAction: ViewAction) {
        when (viewAction) {
            is ViewAction.SearchList -> handleSearchList(viewAction.board)
        }
    }

    private fun handleSearchList(board: String) {
        viewModelScope.launch {
            when (val value = getHistoric(board)) {
                is ApiResponse.Error -> handleError()
                is ApiResponse.Success -> handleSuccess(value.data)
            }
        }
    }

    private fun handleError() {
        viewState.value = ViewState.SetupEmptyList
    }

    private fun handleSuccess(data: List<HistoricModel>) {
        if (data.isEmpty())
            viewState.value = ViewState.SetupEmptyList
        else
            viewState.value = ViewState.SetupList(data)
    }
}