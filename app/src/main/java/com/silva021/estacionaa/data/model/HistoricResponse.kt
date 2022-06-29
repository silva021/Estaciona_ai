package com.silva021.estacionaa.data.model

data class HistoricResponse(
    val time: String,
    val paid: Boolean,
    val left: Boolean,
    val plate: String,
    val reservation: String
)
