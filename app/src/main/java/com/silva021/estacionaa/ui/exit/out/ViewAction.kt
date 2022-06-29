package com.silva021.estacionaa.ui.exit.out

sealed class ViewAction {
    class ConfirmOut(val plate: String) : ViewAction()
}
