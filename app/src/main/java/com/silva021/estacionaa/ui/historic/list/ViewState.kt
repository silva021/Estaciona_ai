package com.silva021.estacionaa.ui.historic.list

import com.silva021.estacionaa.domain.model.HistoricModel

sealed class ViewState {
    object SetupEmptyList : ViewState()
    class SetupList(val list: List<HistoricModel>) : ViewState()
}