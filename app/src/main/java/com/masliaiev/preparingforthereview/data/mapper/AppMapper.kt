package com.masliaiev.preparingforthereview.data.mapper

import com.masliaiev.preparingforthereview.data.database.models.*
import com.masliaiev.preparingforthereview.data.database.relationships.EmployeeAndAutomobileDbModel
import com.masliaiev.preparingforthereview.data.database.relationships.EmployeeWithJobTitlesDbModel
import com.masliaiev.preparingforthereview.data.database.relationships.EmployeeWithPhoneNumbersDbModel
import com.masliaiev.preparingforthereview.data.database.relationships.JobTitleWithEmployeesDbModel
import com.masliaiev.preparingforthereview.domain.entity.*
import com.masliaiev.preparingforthereview.domain.entity.relationships.EmployeeAndAutomobile
import com.masliaiev.preparingforthereview.domain.entity.relationships.EmployeeWithJobTitles
import com.masliaiev.preparingforthereview.domain.entity.relationships.EmployeeWithPhoneNumbers
import com.masliaiev.preparingforthereview.domain.entity.relationships.JobTitleWithEmployees

class AppMapper {

    fun mapAutomobileEntityToAutomobileDbModel(automobile: Automobile): AutomobileDbModel {
        return AutomobileDbModel(
            automobileId = automobile.automobileId,
            brand = automobile.brand,
            employeeId = automobile.employeeId
        )
    }

    fun mapAutomobileDbModelToAutomobileEntity(automobileDbModel: AutomobileDbModel): Automobile {
            return Automobile(
                automobileId = automobileDbModel.automobileId,
                brand = automobileDbModel.brand,
                employeeId = automobileDbModel.employeeId
            )
    }

    fun mapEmployeeEntityToEmployeeDbModel(employee: Employee): EmployeeDbModel {
        return EmployeeDbModel(
            id = employee.id,
            firstName = employee.firstName,
            lastName = employee.lastName,
            address = employee.address,
            automobileId = employee.automobileId
        )
    }

    fun mapEmployeeDbModelToEmployeeEntity(employeeDbModel: EmployeeDbModel): Employee {
        return Employee(
            id = employeeDbModel.id,
            firstName = employeeDbModel.firstName,
            lastName = employeeDbModel.lastName,
            address = employeeDbModel.address,
            automobileId = employeeDbModel.automobileId
        )
    }

    fun mapJobTitleEntityToJobTitleDbModel(jobTitle: JobTitle): JobTitleDbModel {
        return JobTitleDbModel(jobTitle = jobTitle.jobTitle, jobTitleId = jobTitle.jobTitleId)
    }

    fun mapJobTitleDbModelToJobTitleEntity(jobTitleDbModel: JobTitleDbModel): JobTitle {
        return JobTitle(
            jobTitle = jobTitleDbModel.jobTitle,
            jobTitleId = jobTitleDbModel.jobTitleId
        )
    }

    fun mapPhoneNumberEntityToPhoneNumberDbModel(phoneNumber: PhoneNumber): PhoneNumberDbModel {
        return PhoneNumberDbModel(number = phoneNumber.number, employeeId = phoneNumber.employeeId)
    }

    fun mapPhoneNumberDbModelToPhoneNumberEntity(phoneNumberDbModel: PhoneNumberDbModel): PhoneNumber {
        return PhoneNumber(
            number = phoneNumberDbModel.number,
            employeeId = phoneNumberDbModel.employeeId
        )
    }

    fun mapEmployeeJobTitleEntityToEmployeeJobTitleDbModel(employeeJobTitle: EmployeeJobTitle): EmployeeJobTitleDbModel {
        return EmployeeJobTitleDbModel(
            id = employeeJobTitle.id,
            jobTitleId = employeeJobTitle.jobTitleId
        )
    }

    fun mapEmployeeJobTitleDbModelToEmployeeJobTitleEntity(employeeJobTitleDbModel: EmployeeJobTitleDbModel): EmployeeJobTitle {
        return EmployeeJobTitle(
            id = employeeJobTitleDbModel.id,
            jobTitleId = employeeJobTitleDbModel.jobTitleId
        )
    }


    fun mapEmployeeAndAutomobileDbModelToEmployeeAdnAutomobileDbModelEntity(
        employeeAndAutomobileDbModel: EmployeeAndAutomobileDbModel
    ): EmployeeAndAutomobile {
        return EmployeeAndAutomobile(
            employee = mapEmployeeDbModelToEmployeeEntity(employeeAndAutomobileDbModel.employee),
            automobile = mapAutomobileDbModelToAutomobileEntity(employeeAndAutomobileDbModel.automobile)
        )
    }

    fun mapEmployeeWithJobTitlesDbModelEmployeeWithJobTitlesEntity(employeeWithJobTitlesDbModel: EmployeeWithJobTitlesDbModel): EmployeeWithJobTitles {
        return EmployeeWithJobTitles(
            employee = mapEmployeeDbModelToEmployeeEntity(employeeWithJobTitlesDbModel.employee),
            jobTitles = employeeWithJobTitlesDbModel.jobTitles.map {
                mapJobTitleDbModelToJobTitleEntity(it)
            }
        )
    }

    fun mapEmployeeWithPhoneNumbersDbModelToEmployeeWithPhoneNumbersEntity(
        employeeWithPhoneNumbersDbModel: EmployeeWithPhoneNumbersDbModel
    ): EmployeeWithPhoneNumbers {
        return EmployeeWithPhoneNumbers(
            mapEmployeeDbModelToEmployeeEntity(employeeWithPhoneNumbersDbModel.employee),
            employeeWithPhoneNumbersDbModel.phoneNumbers.map {
                mapPhoneNumberDbModelToPhoneNumberEntity(it)
            }
        )
    }

    fun mapJobTitleWithEmployeesDbModelToJobTitleWithEmployeesEntity(jobTitleWithEmployeesDbModel: JobTitleWithEmployeesDbModel): JobTitleWithEmployees {
        return JobTitleWithEmployees(
            mapJobTitleDbModelToJobTitleEntity(jobTitleWithEmployeesDbModel.jobTitle),
            jobTitleWithEmployeesDbModel.employees.map {
                mapEmployeeDbModelToEmployeeEntity(it)
            }
        )
    }
}