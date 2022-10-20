package com.rave.rickandmortyv2.screens.episodes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesListViewModel@Inject constructor(
    private val repo: RepositoryImpl
): ViewModel() {
    private val _char: MutableStateFlow<Resource<Character>> = MutableStateFlow(Resource.Loading)
    val char = _char.asStateFlow()

    private val _episode: MutableStateFlow<Resource<Episode>> = MutableStateFlow(Resource.Loading)
    val episode = _episode.asStateFlow()

    fun setCharacter(id: Int) = viewModelScope.launch {
        _char.value = repo.getCharacterById(id)
    }

    fun setEpisode(id: Int) = viewModelScope.launch {
        _episode.value = repo.getEpisodeById(id)
    }
}