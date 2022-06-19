package com.masliaiev.preparingforthereview.data.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.masliaiev.preparingforthereview.data.database.models.EmployeeDbModel
import com.masliaiev.preparingforthereview.data.database.models.PhoneNumberDbModel

data class EmployeeWithPhoneNumbersDbModel(
    @Embedded
    val employee: EmployeeDbModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "employeeId"
    )
    val phoneNumbers: List<PhoneNumberDbModel>
)