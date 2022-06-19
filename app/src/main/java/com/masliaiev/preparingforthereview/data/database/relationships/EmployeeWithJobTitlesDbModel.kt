package com.masliaiev.preparingforthereview.data.database.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.masliaiev.preparingforthereview.data.database.models.EmployeeDbModel
import com.masliaiev.preparingforthereview.data.database.models.EmployeeJobTitleDbModel
import com.masliaiev.preparingforthereview.data.database.models.JobTitleDbModel

data class EmployeeWithJobTitlesDbModel(
    @Embedded
    val employee: EmployeeDbModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "jobTitleId",
        associateBy = Junction(EmployeeJobTitleDbModel::class)
    )
    val jobTitles: List<JobTitleDbModel>
)