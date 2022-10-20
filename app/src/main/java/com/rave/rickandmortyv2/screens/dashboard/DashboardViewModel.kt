package com.rave.rickandmortyv2.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.CharacterListWrapper
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: RepositoryImpl
) : ViewModel() {
    private val _chars: MutableStateFlow<Resource<CharacterListWrapper>> =
        MutableStateFlow(Resource.Loading)
    val chars = _chars.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() = viewModelScope.launch {
        _chars.value = repo.getCharacters()
    }

}