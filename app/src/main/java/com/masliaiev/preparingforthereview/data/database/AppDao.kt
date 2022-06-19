package com.masliaiev.preparingforthereview.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.masliaiev.preparingforthereview.data.database.models.*
import com.masliaiev.preparingforthereview.data.database.relationships.EmployeeAndAutomobileDbModel
import com.masliaiev.preparingforthereview.data.database.relationships.EmployeeWithJobTitlesDbModel
import com.masliaiev.preparingforthereview.data.database.relationships.EmployeeWithPhoneNumbersDbModel
import com.masliaiev.preparingforthereview.data.database.relationships.JobTitleWithEmployeesDbModel

@Dao
interface AppDao {

    //CREATE

    @Insert
    suspend fun insertJobTitles(jobTitlesDbModel: List<JobTitleDbModel>)

    //READ

    @Query("SELECT * FROM employees")
    fun getEmployees(): LiveData<List<EmployeeDbModel>>

    @Query("SELECT * FROM employees")
    suspend fun getEmployeesList(): List<EmployeeDbModel>

    @Query("SELECT * FROM job_titles")
    suspend fun getJobTitles(): List<JobTitleDbModel>

    @Query("SELECT * FROM automobiles")
    suspend fun getAutomobilesList(): List<AutomobileDbModel>

    @Query("SELECT * FROM phone_numbers")
    suspend fun getPhoneNumbersList(): List<PhoneNumberDbModel>

    //one to one
    @Transaction
    @Query("SELECT * FROM employees WHERE id == :employeeId")
    suspend fun getEmployeeAndAutomobile(employeeId: Int): EmployeeAndAutomobileDbModel

    //one to many
    @Transaction
    @Query("SELECT * FROM employees WHERE id == :employeeId")
    suspend fun getEmployeeWithPhoneNumbers(employeeId: Int): EmployeeWithPhoneNumbersDbModel

    //many to many
    @Transaction
    @Query("SELECT * FROM employees WHERE id == :employeeId")
    suspend fun getEmployeeWithJobTitles(employeeId: Int): EmployeeWithJobTitlesDbModel

    @Transaction
    @Query("SELECT * FROM job_titles WHERE jobTitleId == :titleId")
    suspend fun getJobTitleWithEmployees(titleId: Int): JobTitleWithEmployeesDbModel

    //UPDATE

    @Update
    suspend fun updateEmployee(employeeDbModel: EmployeeDbModel)

    @Update
    suspend fun updateAutomobile(automobileDbModel: AutomobileDbModel)

    @Update
    suspend fun updatePhoneNumber(phoneNumberDbModel: PhoneNumberDbModel)

    @Update
    suspend fun updateJobTitle(jobTitleDbModel: JobTitleDbModel)

    //DELETE

    @Delete
    suspend fun deleteEmployee(employeeDbModel: EmployeeDbModel)

    @Delete
    suspend fun deleteAutomobile(automobileDbModel: AutomobileDbModel)

    @Delete
    suspend fun deletePhoneNumber(phoneNumberDbModel: PhoneNumberDbModel)

    @Delete
    suspend fun deleteJobTitle(jobTitleDbModel: JobTitleDbModel)
}