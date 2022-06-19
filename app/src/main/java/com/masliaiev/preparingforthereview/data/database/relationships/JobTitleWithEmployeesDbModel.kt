package com.masliaiev.preparingforthereview.data.database.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.masliaiev.preparingforthereview.data.database.models.EmployeeDbModel
import com.masliaiev.preparingforthereview.data.database.models.EmployeeJobTitleDbModel
import com.masliaiev.preparingforthereview.data.database.models.JobTitleDbModel

data class JobTitleWithEmployeesDbModel(
    @Embedded
    val jobTitle: JobTitleDbModel,
    @Relation(
        parentColumn = "jobTitleId",
        entityColumn = "id",
        associateBy = Junction(EmployeeJobTitleDbModel::class)
    )
    val employees: List<EmployeeDbModel>
)