package com.lib_data.domain.use_cases

import com.lib_data.domain.models.EpisodeDetails
import com.lib_data.domain.repository.Repository
import com.lib_data.resources.Resource
import javax.inject.Inject

class GetEpisodeByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id : Int): Resource<EpisodeDetails> = repository.getEpisodeById(id)
}