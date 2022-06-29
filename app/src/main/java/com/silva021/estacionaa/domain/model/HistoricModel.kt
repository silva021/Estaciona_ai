package com.silva021.estacionaa.domain.model

import java.io.Serializable

data class HistoricModel(
    val time: String,
    val paid: String,
    val left: String,
    val plate: String,
    val reservation: String
): Serializable
