package com.masliaiev.preparingforthereview.domain.entity.relationships

import com.masliaiev.preparingforthereview.domain.entity.Employee
import com.masliaiev.preparingforthereview.domain.entity.PhoneNumber

data class EmployeeWithPhoneNumbers(
    val employee: Employee,
    val phoneNumbers: List<PhoneNumber>
)