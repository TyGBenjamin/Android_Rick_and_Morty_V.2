package com.rave.rickandmortyv2.presentation.screens.dashboard

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.CharacterWrapper
import com.lib_data.domain.models.EpisodeCharactersInfo
import com.lib_data.domain.models.EpisodeDetails
import com.lib_data.domain.repository.Repository
import com.lib_data.domain.use_cases.GetAllCharactersUseCase
import com.lib_data.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: Repository,
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
): ViewModel() {
    private var _characterList : MutableStateFlow<PagingData<CharacterDetails>?> = MutableStateFlow(null)
    val characterList = _characterList.asStateFlow()
    private var _firstSeenIn : MutableStateFlow<Resource<EpisodeDetails>?> = MutableStateFlow(null)
    val firstSeenIn = _firstSeenIn.asStateFlow()

    init{
        getAllCharacters()
    }

    private fun getAllCharacters() = viewModelScope.launch{
        getAllCharactersUseCase().collect{
            _characterList.value = it
        }
    }

    fun getEpisodeById(id: Int) {
//        when(repo.getEpisodeById(id)){
//            is Resource.Error -> return ""
//            Resource.Idle -> return ""
//            Resource.Loading -> return ""
//            is Resource.Success -> return ""
//        }
//        Log.d(TAG, "getEpisodeById: first seen in $firstSeenIn")
    }
}