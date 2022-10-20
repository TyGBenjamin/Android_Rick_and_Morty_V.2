package com.lib_data.domain.use_cases

import com.lib_data.domain.repository.Repository
import javax.inject.Inject

class GetAllEpisodesUseCase @Inject constructor(
    private val repository: Repository
) {
//    suspend operator fun invoke() : Flow<PagingData<EpisodeDetails>> = repository.getAllEpisodes()
}