package com.silva021.estacionaa.ui.entrance

sealed class ViewAction {
    class ValidateBoard(val value: String) : ViewAction()
    class ClickConfirmEntrance(val board: String) : ViewAction()
}
