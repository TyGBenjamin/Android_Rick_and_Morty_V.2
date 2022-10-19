package com.rave.rickandmortyv2.presentation.screens.character_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.repository.Repository
import com.lib_data.domain.use_cases.GetCharacterByIdUseCase
import com.lib_data.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
): ViewModel() {
    private var _characterDetails : MutableStateFlow<Resource<CharacterDetails>> = MutableStateFlow(Resource.Idle)
    val characterDetails = _characterDetails.asStateFlow()

    fun getCharacterById(id: Int){
        viewModelScope.launch {
            _characterDetails.value = getCharacterByIdUseCase.invoke(id)
        }
    }
}