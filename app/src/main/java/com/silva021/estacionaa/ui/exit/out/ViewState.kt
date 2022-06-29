package com.silva021.estacionaa.ui.exit.out

sealed class ViewState {
    object LoadingScreen : ViewState()
    object ErrorScreen : ViewState()
    object SuccessScreen : ViewState()
    object DismissScreen : ViewState()
}