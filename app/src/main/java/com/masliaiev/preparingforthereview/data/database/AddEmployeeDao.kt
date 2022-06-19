package com.masliaiev.preparingforthereview.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.masliaiev.preparingforthereview.data.database.models.AutomobileDbModel
import com.masliaiev.preparingforthereview.data.database.models.EmployeeDbModel
import com.masliaiev.preparingforthereview.data.database.models.EmployeeJobTitleDbModel
import com.masliaiev.preparingforthereview.data.database.models.PhoneNumberDbModel
import com.masliaiev.preparingforthereview.helpers.responces.DatabaseResponse

@Dao
abstract class AddEmployeeDao {

    //CREATE

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertEmployee(employeeDbModel: EmployeeDbModel)

    @Insert
    abstract suspend fun insertAutomobile(automobileDbModel: AutomobileDbModel)

    @Insert
    abstract suspend fun insertPhoneNumbers(phoneNumbersDbModel: List<PhoneNumberDbModel>)

    @Insert
    abstract suspend fun insertEmployeeJobTitles(employeeJobTitlesDbModel: List<EmployeeJobTitleDbModel>)

    @Transaction
    open suspend fun addEmployee(
        employeeDbModel: EmployeeDbModel,
        automobileDbModel: AutomobileDbModel,
        phoneNumbersDbModel: List<PhoneNumberDbModel>,
        employeeJobTitlesDbModel: List<EmployeeJobTitleDbModel>
    ): DatabaseResponse {
        return try {
            insertEmployee(employeeDbModel)
            insertAutomobile(automobileDbModel)
            insertPhoneNumbers(phoneNumbersDbModel)
            insertEmployeeJobTitles(employeeJobTitlesDbModel)
            DatabaseResponse.SUCCESS
        } catch (throwable: Throwable) {
            DatabaseResponse.ERROR
        }
    }
}