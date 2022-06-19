package com.masliaiev.preparingforthereview.domain.entity.relationships

import com.masliaiev.preparingforthereview.domain.entity.Employee
import com.masliaiev.preparingforthereview.domain.entity.JobTitle

data class JobTitleWithEmployees(
    val jobTitle: JobTitle,
    val employees: List<Employee>
)