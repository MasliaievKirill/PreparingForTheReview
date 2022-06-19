package com.masliaiev.preparingforthereview.di

import com.masliaiev.preparingforthereview.data.database.AppDatabase
import com.masliaiev.preparingforthereview.data.mapper.AppMapper
import com.masliaiev.preparingforthereview.data.repository.AppRepositoryImpl
import com.masliaiev.preparingforthereview.domain.repository.AppRepository
import com.masliaiev.preparingforthereview.domain.usecases.*
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val koinModule = module {

    factory { AppMapper() }

    single { AppDatabase.getInstance(androidApplication()).appDao() }
    single { AppDatabase.getInstance(androidApplication()).addEmployeeDao() }

    single<AppRepository> {
        AppRepositoryImpl(
            appDao = get(),
            mapper = get(),
            addEmployeeDao = get()
        )
    }

    //Use cases

    factory { GetEmployeesUseCase(repository = get()) }
    factory { AddJobTitleUseCase(repository = get()) }
    factory { GetJobTitlesUseCase(repository = get()) }
    factory { AddEmployeeUseCase(repository = get()) }
    factory { GetEmployeesListUseCase(repository = get()) }
    factory { GetAutomobilesListUseCase(repository = get()) }
    factory { GetPhoneNumbersListUseCase(repository = get()) }
    factory { DeleteEmployeeUseCase(repository = get()) }

}