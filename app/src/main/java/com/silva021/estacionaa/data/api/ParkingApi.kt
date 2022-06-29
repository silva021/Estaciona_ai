package com.silva021.estacionaa.data.api

import com.silva021.estacionaa.data.model.EntranceResponse
import com.silva021.estacionaa.data.model.HistoricResponse
import com.silva021.estacionaa.data.model.PlateBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ParkingApi {

    @POST("/parking")
    @Headers("Content-Type: application/json")
    suspend fun registerEntranceVehicle(@Body plate: PlateBody): EntranceResponse

    @POST("/parking/{board}/pay")
    suspend fun registerPaymentVehicle(
        @Path("board") board: String,
    ): Response<Unit>

    @POST("/parking/{board}/out")
    suspend fun registerExitVehicle(
        @Path("board") board: String,
    ): Response<Unit>

    @GET("/parking/{board}")
    suspend fun getHistoricVehicle(
        @Path("board") board: String,
    ): List<HistoricResponse>
}
