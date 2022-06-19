package com.masliaiev.preparingforthereview.domain.entity.relationships

import com.masliaiev.preparingforthereview.data.database.models.EmployeeDbModel
import com.masliaiev.preparingforthereview.domain.entity.Automobile
import com.masliaiev.preparingforthereview.domain.entity.Employee

data class EmployeeAndAutomobile(
    val employee: Employee,
    val automobile: Automobile
)