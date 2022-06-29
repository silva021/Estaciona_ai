package com.silva021.estacionaa.ui.historic.list

sealed class ViewAction {
    class SearchList(val board: String) : ViewAction()
}
