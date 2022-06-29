package com.silva021.estacionaa.domain.usecase

import com.silva021.estacionaa.data.api.ApiResponse
import com.silva021.toolkit.model.ResultError
import com.silva021.estacionaa.data.repository.ParkingRepository
import com.silva021.estacionaa.domain.mapper.HistoricResponseToModelMapper
import com.silva021.estacionaa.domain.model.HistoricModel
import retrofit2.HttpException

class GetHistoricVehicle(
    private val repository: ParkingRepository,
    private val mapper: HistoricResponseToModelMapper,
) : GetHistoricVehicleUseCase {
    override suspend fun invoke(board: String): ApiResponse<List<HistoricModel>> {
        return try {
            val getHistoric = repository.getHistoricVehicle(board)
            ApiResponse.Success(
                getHistoric.map {
                    mapper.modelToResponse(it)
                }
            )
        } catch (e: HttpException) {
            ApiResponse.Error(ResultError.HttpError(e.code()))
        }
    }
}

interface GetHistoricVehicleUseCase {
    suspend operator fun invoke(board: String): ApiResponse<List<HistoricModel>>
}