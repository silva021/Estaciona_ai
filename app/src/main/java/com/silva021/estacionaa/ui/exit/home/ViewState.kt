package com.silva021.estacionaa.ui.exit.home

sealed class ViewState {
    object ErrorScreen : ViewState()
    class EnableButton(val state: Boolean) : ViewState()
}