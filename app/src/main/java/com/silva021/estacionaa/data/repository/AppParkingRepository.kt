package com.silva021.estacionaa.data.repository

import com.silva021.estacionaa.data.api.ParkingApi
import com.silva021.estacionaa.data.model.PlateBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppParkingRepository(
    private val parkingApi: ParkingApi,
) : ParkingRepository {
    override suspend fun registerEntranceVehicle(board: String) =
        withContext(Dispatchers.IO) {
            parkingApi.registerEntranceVehicle(
                PlateBody(board)
            )
        }

    override suspend fun registerPaymentVehicle(board: String) =
        withContext(Dispatchers.IO) {
            parkingApi.registerPaymentVehicle(board)
        }

    override suspend fun registerExitVehicle(board: String) =
        withContext(Dispatchers.IO) {
            parkingApi.registerExitVehicle(board)
        }

    override suspend fun getHistoricVehicle(board: String) =
        withContext(Dispatchers.IO) {
            parkingApi.getHistoricVehicle(board)
        }
}