package com.rave.rickandmortyv2.presentation.screen.episode_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EpisodeDetailsViewModel @Inject constructor(
    private val repo : RepositoryImpl
): ViewModel(){

    private val _charDetails: MutableStateFlow<Resource<Character>> = MutableStateFlow(Resource.Loading)
    val char = _charDetails.asStateFlow()

    private val _episodeDetails: MutableStateFlow<Resource<Episode>> = MutableStateFlow(Resource.Loading)
    val episode = _episodeDetails.asStateFlow()

    fun getCharacterDetails(charId: String){
        viewModelScope.launch {
            _charDetails.value = repo.getCharactersById(charId)
        }
    }

    fun getEpisodeDetails(episodeId: Int){
        viewModelScope.launch {
            _episodeDetails.value = repo.getEpisodeById(episodeId)
        }
    }

}