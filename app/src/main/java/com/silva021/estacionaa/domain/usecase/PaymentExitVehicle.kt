package com.silva021.estacionaa.domain.usecase

import com.silva021.estacionaa.data.api.ApiResponse
import com.silva021.toolkit.model.ResultError
import com.silva021.estacionaa.data.repository.ParkingRepository
import retrofit2.HttpException

class PaymentExitVehicle(
    private val repository: ParkingRepository,
) : PaymentExitVehicleUseCase {
    override suspend fun invoke(board: String): ApiResponse<Boolean> {
        return try {
            if (repository.registerPaymentVehicle(board).isSuccessful)
                ApiResponse.Success(true)
            else
                ApiResponse.Success(false)
        } catch (e: HttpException) {
            ApiResponse.Error(ResultError.HttpError(e.code()))
        }
    }
}

interface PaymentExitVehicleUseCase {
    suspend operator fun invoke(board: String): ApiResponse<Boolean>
}