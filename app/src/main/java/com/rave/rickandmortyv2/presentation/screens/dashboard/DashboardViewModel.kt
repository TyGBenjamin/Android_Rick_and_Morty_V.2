package com.rave.rickandmortyv2.presentation.screens.dashboard

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.EpisodeDetails
import com.lib_data.domain.repository.Repository
import com.lib_data.domain.use_cases.GetAllCharactersUseCase
import com.lib_data.domain.use_cases.GetAllEpisodesUseCase
import com.lib_data.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val repo: Repository,
): ViewModel() {
    private var _characterList : MutableStateFlow<PagingData<CharacterDetails>?> = MutableStateFlow(null)
    val characterList = _characterList.asStateFlow()

    init{
        getAllCharacters()
    }

    private fun getAllCharacters() = viewModelScope.launch{
        getAllCharactersUseCase().collect{
            _characterList.value = it
        }
    }

    fun getEpisodeById(id: Int): String {
        var name = "Pilot"
        viewModelScope.launch {
            when(val episodeDetails = repo.getEpisodeById(id)){
                is Resource.Idle -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    name = episodeDetails.data.name
                }
                else -> name
            }
        }.also {
            return name
        }
    }

}