package com.masliaiev.preparingforthereview.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class EmployeeDbModel(
    @PrimaryKey
    val id: Int,
    val firstName: String,
    val lastName: String,
    val address: String,
    val automobileId: Int
)