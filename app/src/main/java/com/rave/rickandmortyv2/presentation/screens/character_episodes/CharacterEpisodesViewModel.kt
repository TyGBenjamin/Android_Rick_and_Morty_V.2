package com.rave.rickandmortyv2.presentation.screens.character_episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.EpisodeDetails
import com.lib_data.domain.models.EpisodeWrapper
import com.lib_data.domain.use_cases.GetCharacterByIdUseCase
import com.lib_data.domain.use_cases.GetEpisodeByIdUseCase
import com.lib_data.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterEpisodesViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase,
): ViewModel() {
    private var _characterDetails : MutableStateFlow<Resource<CharacterDetails>> = MutableStateFlow(Resource.Idle)
    val characterDetails = _characterDetails.asStateFlow()
    private var _episodeList : MutableStateFlow<Resource<EpisodeDetails>> = MutableStateFlow(Resource.Idle)
    val episodeList = _episodeList.asStateFlow()

    fun getCharacterById(id: Int){
        viewModelScope.launch {
            _characterDetails.value = getCharacterByIdUseCase.invoke(id)
        }
    }

    fun getEpisodesById(id: Int){
        viewModelScope.launch {
            _episodeList.value = getEpisodeByIdUseCase.invoke(id)
        }
    }
}