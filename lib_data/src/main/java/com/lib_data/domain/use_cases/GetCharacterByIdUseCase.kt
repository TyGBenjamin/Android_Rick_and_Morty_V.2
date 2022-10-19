package com.lib_data.domain.use_cases

import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.repository.Repository
import com.lib_data.resources.Resource
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int): Resource<CharacterDetails> = repository.getCharacterById(id)
}