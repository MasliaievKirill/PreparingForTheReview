package com.masliaiev.preparingforthereview.domain.usecases

import com.masliaiev.preparingforthereview.domain.entity.Automobile
import com.masliaiev.preparingforthereview.domain.entity.PhoneNumber
import com.masliaiev.preparingforthereview.domain.repository.AppRepository

class GetPhoneNumbersListUseCase(
    private val repository: AppRepository
) {
    suspend fun getPhoneNumbersList(): List<PhoneNumber> {
        return repository.getPhoneNumbersList()
    }
}