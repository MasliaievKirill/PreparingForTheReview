package com.masliaiev.preparingforthereview.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "employee_job_title",
    primaryKeys = ["id", "jobTitleId"],
    foreignKeys = [
        ForeignKey(
            entity = EmployeeDbModel::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = JobTitleDbModel::class,
            parentColumns = ["jobTitleId"],
            childColumns = ["jobTitleId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class EmployeeJobTitleDbModel(
    val id: Int,
    val jobTitleId: Int
)