package com.masliaiev.preparingforthereview.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "job_titles")
data class JobTitleDbModel(
    @PrimaryKey
    val jobTitleId: Int,
    val jobTitle: String
)