package com.lib_data.domain.use_cases

import com.lib_data.domain.models.LocationDetails
import com.lib_data.domain.repository.Repository
import com.lib_data.resources.Resource
import javax.inject.Inject

class GetLocationDetailsByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int): Resource<LocationDetails> = repository.getLocationDetailsById(id)
}