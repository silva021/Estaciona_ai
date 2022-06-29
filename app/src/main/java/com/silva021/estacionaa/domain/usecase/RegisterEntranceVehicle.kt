package com.silva021.estacionaa.domain.usecase

import com.silva021.estacionaa.data.api.ApiResponse
import com.silva021.toolkit.model.ResultError
import com.silva021.estacionaa.data.model.EntranceResponse
import com.silva021.estacionaa.data.repository.ParkingRepository
import retrofit2.HttpException

class RegisterEntranceVehicle(
    private val repository: ParkingRepository
) : RegisterEntranceVehicleUseCase{
    override suspend fun invoke(board: String): ApiResponse<EntranceResponse> {
        return try {
            val registerEntranceVehicle = repository.registerEntranceVehicle(board)
            ApiResponse.Success(registerEntranceVehicle)
        } catch (e: HttpException) {
            ApiResponse.Error(ResultError.HttpError(e.code()))
        }
    }
}

interface RegisterEntranceVehicleUseCase {
    suspend operator fun invoke(board: String): ApiResponse<EntranceResponse>
}