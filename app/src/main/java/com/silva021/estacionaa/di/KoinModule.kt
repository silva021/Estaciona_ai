package com.silva021.estacionaa.di

import com.silva021.estacionaa.data.api.ParkingApi
import com.silva021.toolkit.model.createApi
import com.silva021.estacionaa.data.repository.AppParkingRepository
import com.silva021.estacionaa.data.repository.ParkingRepository
import com.silva021.estacionaa.domain.mapper.HistoricResponseToModel
import com.silva021.estacionaa.domain.mapper.HistoricResponseToModelMapper
import com.silva021.estacionaa.domain.usecase.ConfirmOutVehicle
import com.silva021.estacionaa.domain.usecase.ConfirmOutVehicleUseCase
import com.silva021.estacionaa.domain.usecase.GetHistoricVehicle
import com.silva021.estacionaa.domain.usecase.GetHistoricVehicleUseCase
import com.silva021.estacionaa.domain.usecase.PaymentExitVehicle
import com.silva021.estacionaa.domain.usecase.PaymentExitVehicleUseCase
import com.silva021.estacionaa.domain.usecase.RegisterEntranceVehicle
import com.silva021.estacionaa.domain.usecase.RegisterEntranceVehicleUseCase
import com.silva021.estacionaa.domain.usecase.ValidateTransitBoard
import com.silva021.estacionaa.domain.usecase.ValidateTransitBoardUseCase
import com.silva021.estacionaa.ui.entrance.EntranceViewModel
import com.silva021.estacionaa.ui.exit.out.OutDialogViewModel
import com.silva021.estacionaa.ui.exit.home.ExitViewModel
import com.silva021.estacionaa.ui.exit.payment.PaymentDialogViewModel
import com.silva021.estacionaa.ui.historic.list.HistoricListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // ViewModel
    viewModel { EntranceViewModel(get(), get()) }
    viewModel { ExitViewModel(get()) }
    viewModel { HistoricListViewModel(get()) }
    viewModel { PaymentDialogViewModel(get()) }
    viewModel { OutDialogViewModel(get()) }

    // Usecase
    factory<ValidateTransitBoardUseCase> { ValidateTransitBoard() }
    factory<RegisterEntranceVehicleUseCase> { RegisterEntranceVehicle(get()) }
    factory<GetHistoricVehicleUseCase> { GetHistoricVehicle(get(), get()) }
    factory<PaymentExitVehicleUseCase> { PaymentExitVehicle(get()) }
    factory<ConfirmOutVehicleUseCase> { ConfirmOutVehicle(get()) }


    // Mapper
    factory<HistoricResponseToModelMapper> { HistoricResponseToModel() }

    // Data
    single<ParkingRepository> { AppParkingRepository(get()) }
    single { createApi<ParkingApi>() }
}