package com.silva021.estacionaa.ui.exit.payment

sealed class ViewState {
    object LoadingScreen : ViewState()
    object SuccessScreen : ViewState()
    object Error : ViewState()
    object DismissScreen : ViewState()
}