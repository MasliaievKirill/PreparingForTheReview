package com.masliaiev.preparingforthereview.domain.usecases

import com.masliaiev.preparingforthereview.domain.entity.Employee
import com.masliaiev.preparingforthereview.domain.repository.AppRepository

class DeleteEmployeeUseCase(
    private val repository: AppRepository
) {
    suspend fun deleteEmployee(employee: Employee){
        repository.deleteEmployee(employee)
    }
}