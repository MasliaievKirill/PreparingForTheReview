package com.masliaiev.preparingforthereview.data.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.masliaiev.preparingforthereview.data.database.models.AutomobileDbModel
import com.masliaiev.preparingforthereview.data.database.models.EmployeeDbModel

data class EmployeeAndAutomobileDbModel(
    @Embedded
    val employee: EmployeeDbModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "automobileId"
    )
    val automobile: AutomobileDbModel
)