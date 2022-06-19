package com.masliaiev.preparingforthereview.domain.repository

import androidx.lifecycle.LiveData
import com.masliaiev.preparingforthereview.domain.entity.*
import com.masliaiev.preparingforthereview.domain.entity.relationships.EmployeeAndAutomobile
import com.masliaiev.preparingforthereview.domain.entity.relationships.EmployeeWithJobTitles
import com.masliaiev.preparingforthereview.domain.entity.relationships.EmployeeWithPhoneNumbers
import com.masliaiev.preparingforthereview.domain.entity.relationships.JobTitleWithEmployees
import com.masliaiev.preparingforthereview.helpers.responces.DatabaseResponse

interface AppRepository {

    fun getEmployees(): LiveData<List<Employee>>

    suspend fun getEmployeesList(): List<Employee>

    suspend fun getJobTitles(): List<JobTitle>

    suspend fun getAutomobilesList(): List<Automobile>

    suspend fun getPhoneNumbersList(): List<PhoneNumber>

    suspend fun getEmployeeAndAutomobile(employeeId: Int): EmployeeAndAutomobile

    suspend fun getEmployeeWithPhoneNumbers(employeeId: Int): EmployeeWithPhoneNumbers

    suspend fun getEmployeeWithJobTitles(employeeId: Int): EmployeeWithJobTitles

    suspend fun getJobTitleWithEmployees(titleId: Int): JobTitleWithEmployees

    suspend fun addEmployee(
        employee: Employee,
        automobile: Automobile,
        phoneNumbers: List<PhoneNumber>,
        employeeJobTitles: List<EmployeeJobTitle>
    ): DatabaseResponse

    suspend fun addJobTitles(jobTitles: List<JobTitle>)

    suspend fun deleteEmployee(employee: Employee)
}