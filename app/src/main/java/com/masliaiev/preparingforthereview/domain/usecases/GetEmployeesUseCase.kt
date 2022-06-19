package com.masliaiev.preparingforthereview.domain.usecases

import androidx.lifecycle.LiveData
import com.masliaiev.preparingforthereview.domain.entity.Employee
import com.masliaiev.preparingforthereview.domain.repository.AppRepository

class GetEmployeesUseCase(
    private val repository: AppRepository
) {
    fun getEmployees(): LiveData<List<Employee>> {
        return repository.getEmployees()
    }
}