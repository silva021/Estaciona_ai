package com.silva021.estacionaa.ui.entrance

sealed class ViewState {
    class EnableButton(val state: Boolean) : ViewState()
    object Loading : ViewState()
    object LoadingSuccess : ViewState()
    object ErrorScreen : ViewState()
    object SetupScreen : ViewState()
}