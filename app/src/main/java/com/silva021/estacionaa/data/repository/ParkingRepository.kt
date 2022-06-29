package com.silva021.estacionaa.data.repository

import com.silva021.estacionaa.data.model.EntranceResponse
import com.silva021.estacionaa.data.model.HistoricResponse
import retrofit2.Response

interface ParkingRepository {
    suspend fun registerEntranceVehicle(board: String): EntranceResponse
    suspend fun registerPaymentVehicle(board: String): Response<Unit>
    suspend fun registerExitVehicle(board: String): Response<Unit>
    suspend fun getHistoricVehicle(board: String): List<HistoricResponse>
}