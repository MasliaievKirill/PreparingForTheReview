package com.masliaiev.preparingforthereview.domain.usecases

import com.masliaiev.preparingforthereview.domain.entity.Automobile
import com.masliaiev.preparingforthereview.domain.repository.AppRepository

class GetAutomobilesListUseCase(
    private val repository: AppRepository
) {
    suspend fun getAutomobilesList(): List<Automobile> {
        return repository.getAutomobilesList()
    }
}