package com.masliaiev.preparingforthereview.di

import com.masliaiev.preparingforthereview.presentation.viewmodels.AddToDatabaseFragmentViewModel
import com.masliaiev.preparingforthereview.presentation.viewmodels.DatabaseFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        DatabaseFragmentViewModel(
            getEmployeesUseCase = get(),
            addJobTitleUseCase = get(),
            deleteEmployeeUseCase = get()
        )
    }
    viewModel {
        AddToDatabaseFragmentViewModel(
            addEmployeeUseCase = get(),
            getEmployeesListUseCase = get(),
            getAutomobilesListUseCase = get(),
            getPhoneNumbersListUseCase = get()
        )
    }
}