package com.rave.rickandmortyv2.presentation.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lib_data.domain.models.CharacterWrapper
import com.lib_data.domain.repository.CharacterRepository
import com.lib_data.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: CharacterRepository
): ViewModel() {
    private var _characterList : MutableStateFlow<Resource<CharacterWrapper>> = MutableStateFlow(Resource.Idle)
    val characterList = _characterList.asStateFlow()

    init{
        getAllCharacters()
    }

    private fun getAllCharacters() {
        viewModelScope.launch {
            _characterList.value = repo.getAllCharacters()
        }
    }
}