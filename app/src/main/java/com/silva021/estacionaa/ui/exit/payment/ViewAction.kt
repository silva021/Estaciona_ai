package com.silva021.estacionaa.ui.exit.payment

sealed class ViewAction {
    class ConfirmPayment(val plate: String) : ViewAction()
}
