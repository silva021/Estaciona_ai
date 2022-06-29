package com.silva021.estacionaa.domain.usecase

class ValidateTransitBoard : ValidateTransitBoardUseCase {
    override operator fun invoke(board: String): Boolean {
        val onlyNumber = Regex("[0-9]*")
        val onlyString = Regex("[a-zA-Z]*")
        return if (board.length < 8)
            false
        else board.substring(0, 3).matches(onlyString)
                && board.substring(4, 8).matches(onlyNumber)
                && board.substring(3, 4).contains("-")
    }
}

interface ValidateTransitBoardUseCase {
    operator fun invoke(board: String): Boolean
}