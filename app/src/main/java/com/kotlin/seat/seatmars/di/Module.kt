package com.kotlin.seat.seatmars.di

import com.kotlin.seat.seatmars.model.MarsDataContract
import com.kotlin.seat.seatmars.model.MarsLocalData
import com.kotlin.seat.seatmars.model.MarsRepository
import com.kotlin.seat.seatmars.viewModel.MarsTableViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectModule() = loadModule

private val loadModule by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            repositoryModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel { MarsTableViewModel(repository = get()) }
}

val repositoryModule: Module = module {
    single<MarsDataContract.Repository> {
        MarsRepository(local = get())
    }

    single<MarsDataContract.Local> {
        MarsLocalData()
    }
}
