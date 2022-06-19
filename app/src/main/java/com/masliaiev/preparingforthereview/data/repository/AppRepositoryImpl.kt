package com.masliaiev.preparingforthereview.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.masliaiev.preparingforthereview.data.database.AddEmployeeDao
import com.masliaiev.preparingforthereview.data.database.AppDao
import com.masliaiev.preparingforthereview.data.database.models.EmployeeDbModel
import com.masliaiev.preparingforthereview.data.mapper.AppMapper
import com.masliaiev.preparingforthereview.domain.entity.*
import com.masliaiev.preparingforthereview.domain.entity.relationships.EmployeeAndAutomobile
import com.masliaiev.preparingforthereview.domain.entity.relationships.EmployeeWithJobTitles
import com.masliaiev.preparingforthereview.domain.entity.relationships.EmployeeWithPhoneNumbers
import com.masliaiev.preparingforthereview.domain.entity.relationships.JobTitleWithEmployees
import com.masliaiev.preparingforthereview.domain.repository.AppRepository
import com.masliaiev.preparingforthereview.helpers.responces.DatabaseResponse

class AppRepositoryImpl(
    private val appDao: AppDao,
    private val addEmployeeDao: AddEmployeeDao,
    private val mapper: AppMapper
) : AppRepository {

    override fun getEmployees(): LiveData<List<Employee>> {
        return Transformations.map(appDao.getEmployees()) {
            it.map { employee ->
                mapper.mapEmployeeDbModelToEmployeeEntity(employee)
            }
        }
    }

    override suspend fun getEmployeesList(): List<Employee> {
        return appDao.getEmployeesList().map {
            mapper.mapEmployeeDbModelToEmployeeEntity(it)
        }
    }

    override suspend fun getJobTitles(): List<JobTitle> {
        return appDao.getJobTitles().map {
            mapper.mapJobTitleDbModelToJobTitleEntity(it)
        }
    }

    override suspend fun getAutomobilesList(): List<Automobile> {
        return appDao.getAutomobilesList().map {
            mapper.mapAutomobileDbModelToAutomobileEntity(it)
        }
    }

    override suspend fun getPhoneNumbersList(): List<PhoneNumber> {
        return appDao.getPhoneNumbersList().map {
            mapper.mapPhoneNumberDbModelToPhoneNumberEntity(it)
        }
    }

    override suspend fun getEmployeeAndAutomobile(employeeId: Int): EmployeeAndAutomobile {
        return mapper.mapEmployeeAndAutomobileDbModelToEmployeeAdnAutomobileDbModelEntity(
            appDao.getEmployeeAndAutomobile(employeeId)
        )
    }

    override suspend fun getEmployeeWithPhoneNumbers(employeeId: Int): EmployeeWithPhoneNumbers {
        return mapper.mapEmployeeWithPhoneNumbersDbModelToEmployeeWithPhoneNumbersEntity(
            appDao.getEmployeeWithPhoneNumbers(employeeId)
        )
    }

    override suspend fun getEmployeeWithJobTitles(employeeId: Int): EmployeeWithJobTitles {
        return mapper.mapEmployeeWithJobTitlesDbModelEmployeeWithJobTitlesEntity(
            appDao.getEmployeeWithJobTitles(employeeId)
        )
    }

    override suspend fun getJobTitleWithEmployees(titleId: Int): JobTitleWithEmployees {
        return mapper.mapJobTitleWithEmployeesDbModelToJobTitleWithEmployeesEntity(
            appDao.getJobTitleWithEmployees(titleId)
        )
    }

    override suspend fun addEmployee(
        employee: Employee,
        automobile: Automobile,
        phoneNumbers: List<PhoneNumber>,
        employeeJobTitles: List<EmployeeJobTitle>
    ): DatabaseResponse {
        return addEmployeeDao.addEmployee(
            mapper.mapEmployeeEntityToEmployeeDbModel(employee),
            mapper.mapAutomobileEntityToAutomobileDbModel(automobile),
            phoneNumbers.map {
                mapper.mapPhoneNumberEntityToPhoneNumberDbModel(it)
            },
            employeeJobTitles.map {
                mapper.mapEmployeeJobTitleEntityToEmployeeJobTitleDbModel(it)
            }
        )
    }

    override suspend fun addJobTitles(jobTitles: List<JobTitle>) {
        appDao.insertJobTitles(
            jobTitles.map {
                mapper.mapJobTitleEntityToJobTitleDbModel(it)
            }
        )
    }

    override suspend fun deleteEmployee(employee: Employee) {
        appDao.deleteEmployee(mapper.mapEmployeeEntityToEmployeeDbModel(employee))
    }
}