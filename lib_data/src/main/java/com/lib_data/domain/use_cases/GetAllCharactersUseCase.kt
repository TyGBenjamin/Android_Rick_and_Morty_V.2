package com.lib_data.domain.use_cases


import androidx.paging.PagingData
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.CharacterWrapper
import com.lib_data.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllCharactersUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() : Flow<PagingData<CharacterDetails>> = repository.getAllCharacters()
}