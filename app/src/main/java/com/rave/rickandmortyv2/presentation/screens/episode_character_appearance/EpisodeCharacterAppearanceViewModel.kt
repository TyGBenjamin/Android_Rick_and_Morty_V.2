package com.rave.rickandmortyv2.presentation.screens.episode_character_appearance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.EpisodeDetails
import com.lib_data.domain.use_cases.GetCharacterByIdUseCase
import com.lib_data.domain.use_cases.GetEpisodeByIdUseCase
import com.lib_data.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeCharacterAppearanceViewModel @Inject constructor(
    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
): ViewModel() {
    private var _episodeDetails : MutableStateFlow<Resource<EpisodeDetails>> = MutableStateFlow(Resource.Idle)
    val episodeDetails = _episodeDetails.asStateFlow()
    private var _characterDetails : MutableStateFlow<Resource<CharacterDetails>> = MutableStateFlow(Resource.Idle)
    val characterDetails = _characterDetails.asStateFlow()


    fun getEpisodeById(id: Int){
        viewModelScope.launch {
            _episodeDetails.value = getEpisodeByIdUseCase.invoke(id)
        }
    }

    fun getCharacterById(id: Int){
        viewModelScope.launch {
            _characterDetails.value = getCharacterByIdUseCase.invoke(id)
        }
    }
}