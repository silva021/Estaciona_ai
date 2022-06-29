package com.silva021.estacionaa.domain.usecase

import com.silva021.estacionaa.data.api.ApiResponse
import com.silva021.toolkit.model.ResultError
import com.silva021.estacionaa.data.repository.ParkingRepository
import retrofit2.HttpException

class ConfirmOutVehicle(
    private val repository: ParkingRepository,
) : ConfirmOutVehicleUseCase {
    override suspend fun invoke(board: String): ApiResponse<Boolean> {
        return try {
            ApiResponse.Success(repository.registerExitVehicle(board).isSuccessful)
        } catch (e: HttpException) {
            ApiResponse.Error(ResultError.HttpError(e.code()))
        }
    }
}

interface ConfirmOutVehicleUseCase {
    suspend operator fun invoke(board: String): ApiResponse<Boolean>
}