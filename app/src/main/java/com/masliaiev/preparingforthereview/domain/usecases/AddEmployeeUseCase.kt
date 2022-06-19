package com.masliaiev.preparingforthereview.domain.usecases

import com.masliaiev.preparingforthereview.domain.entity.Automobile
import com.masliaiev.preparingforthereview.domain.entity.Employee
import com.masliaiev.preparingforthereview.domain.entity.EmployeeJobTitle
import com.masliaiev.preparingforthereview.domain.entity.PhoneNumber
import com.masliaiev.preparingforthereview.domain.repository.AppRepository
import com.masliaiev.preparingforthereview.helpers.responces.DatabaseResponse

class AddEmployeeUseCase(
    private val repository: AppRepository
) {

    suspend fun addEmployee(
        employee: Employee,
        automobile: Automobile,
        phoneNumbers: List<PhoneNumber>,
        employeeJobTitles: List<EmployeeJobTitle>
    ): DatabaseResponse {
        return repository.addEmployee(employee, automobile, phoneNumbers, employeeJobTitles)
    }
}