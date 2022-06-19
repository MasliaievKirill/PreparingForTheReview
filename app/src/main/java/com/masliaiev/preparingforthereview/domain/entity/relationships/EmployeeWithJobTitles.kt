package com.masliaiev.preparingforthereview.domain.entity.relationships

import com.masliaiev.preparingforthereview.domain.entity.Employee
import com.masliaiev.preparingforthereview.domain.entity.JobTitle

data class EmployeeWithJobTitles(
    val employee: Employee,
    val jobTitles: List<JobTitle>
)