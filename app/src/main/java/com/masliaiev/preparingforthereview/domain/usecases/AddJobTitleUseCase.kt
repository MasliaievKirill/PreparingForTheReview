package com.masliaiev.preparingforthereview.domain.usecases

import com.masliaiev.preparingforthereview.domain.entity.JobTitle
import com.masliaiev.preparingforthereview.domain.repository.AppRepository

class AddJobTitleUseCase(
    private val repository: AppRepository
) {
    suspend fun addJobTitles(jobTitles: List<JobTitle>) {
        repository.addJobTitles(jobTitles)
    }
}