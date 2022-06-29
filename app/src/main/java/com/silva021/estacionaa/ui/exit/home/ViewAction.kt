package com.silva021.estacionaa.ui.exit.home

sealed class ViewAction {
    class ValidateBoard(val value: String) : ViewAction()
}
