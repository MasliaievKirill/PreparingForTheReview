package com.masliaiev.preparingforthereview.domain.usecases

import androidx.lifecycle.LiveData
import com.masliaiev.preparingforthereview.domain.entity.Employee
import com.masliaiev.preparingforthereview.domain.repository.AppRepository

class GetEmployeesListUseCase(
    private val repository: AppRepository
) {
    suspend fun getEmployeesList(): List<Employee> {
        return repository.getEmployeesList()
    }
}