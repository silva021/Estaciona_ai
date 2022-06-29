package com.silva021.estacionaa.domain.mapper

import com.silva021.estacionaa.data.model.HistoricResponse
import com.silva021.estacionaa.domain.model.HistoricModel

class HistoricResponseToModel : HistoricResponseToModelMapper {
    override fun modelToResponse(response: HistoricResponse) = HistoricModel(
        time = response.time.replace("days", "dias ")
            .replace("hours", "h ")
            .replace("minutes", "min ")
            .replace("seconds", "seg "),
        paid = if (response.paid) "Pago" else "—",
        left = if (response.left) "—" else "Estacionado",
        plate = response.plate,
        reservation = response.reservation
    )
}

interface HistoricResponseToModelMapper {
    fun modelToResponse(response: HistoricResponse): HistoricModel
}