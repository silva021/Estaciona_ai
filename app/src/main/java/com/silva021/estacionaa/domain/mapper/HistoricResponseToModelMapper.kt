package com.silva021.estacionaa.domain.mapper

import com.silva021.estacionaa.data.model.HistoricResponse
import com.silva021.estacionaa.domain.model.HistoricModel

class HistoricResponseToModel : HistoricResponseToModelMapper {
    override fun modelToResponse(response: HistoricResponse) = HistoricModel(
        time = response.time.replace("days", "Dias ")
            .replace("hours", "Horas ")
            .replace("minutes", "Minutos ")
            .replace("seconds", "Segundos"),
        paid = if (response.paid) "Pago" else "—",
        left = if (response.left) "—" else "Estacionado",
        plate = response.plate,
        reservation = response.reservation
    )
}

interface HistoricResponseToModelMapper {
    fun modelToResponse(response: HistoricResponse): HistoricModel
}