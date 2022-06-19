package com.masliaiev.preparingforthereview.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masliaiev.preparingforthereview.domain.entity.Employee
import com.masliaiev.preparingforthereview.domain.usecases.AddJobTitleUseCase
import com.masliaiev.preparingforthereview.domain.usecases.DeleteEmployeeUseCase
import com.masliaiev.preparingforthereview.domain.usecases.GetEmployeesUseCase
import kotlinx.coroutines.launch

class DatabaseFragmentViewModel(
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val addJobTitleUseCase: AddJobTitleUseCase,
    private val deleteEmployeeUseCase: DeleteEmployeeUseCase
) : ViewModel() {

//    init {
//        val jobTitles = listOf(
//            JobTitle(1, "Director"),
//            JobTitle(2, "Software engineer"),
//            JobTitle(3, "Mechanical engineer"),
//            JobTitle(4, "QA")
//        )
//        viewModelScope.launch {
//            addJobTitleUseCase.addJobTitles(jobTitles)
//        }
//    }

    val employeesLiveData = getEmployeesUseCase.getEmployees()

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch {
            deleteEmployeeUseCase.deleteEmployee(employee)
        }
    }
}