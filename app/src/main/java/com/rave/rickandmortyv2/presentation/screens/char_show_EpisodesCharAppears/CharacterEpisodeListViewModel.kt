package com.rave.rickandmortyv2.presentation.screens.char_show_EpisodesCharAppears

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterEpisodeListViewModel @Inject constructor(
    private val repo: RepositoryImpl
) : ViewModel() {
    private val _char: MutableStateFlow<Resource<com.example.lib_data.domain.models.Character>?> =
        MutableStateFlow(Resource.Loading)
    val char = _char.asStateFlow()

    private val _episode: MutableStateFlow<Resource<com.example.lib_data.domain.models.Episode>?> =
        MutableStateFlow(Resource.Loading)
    val episode = _episode.asStateFlow()

    fun setChar(Id: Int) {
        viewModelScope.launch {
            _char.value = repo.getCharacterById(Id)
        }
    }


    fun setEpisode(Id: Int) {
        viewModelScope.launch {
            _episode.value = repo.getEpisodeById(Id)
        }
    }


}