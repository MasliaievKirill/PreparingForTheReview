package com.masliaiev.preparingforthereview.domain.usecases

import com.masliaiev.preparingforthereview.domain.entity.JobTitle
import com.masliaiev.preparingforthereview.domain.repository.AppRepository

class GetJobTitlesUseCase(
    private val repository: AppRepository
) {

    suspend fun getJobTitles(): List<JobTitle> {
        return repository.getJobTitles()
    }
}