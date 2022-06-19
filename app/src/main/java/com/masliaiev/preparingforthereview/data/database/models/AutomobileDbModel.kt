package com.masliaiev.preparingforthereview.data.database.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "automobiles",
    foreignKeys = [
        ForeignKey(
            entity = EmployeeDbModel::class,
            parentColumns = ["id"],
            childColumns = ["employeeId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class AutomobileDbModel(
    @PrimaryKey
    val automobileId: Int,
    val brand: String,
    val employeeId: Int
)