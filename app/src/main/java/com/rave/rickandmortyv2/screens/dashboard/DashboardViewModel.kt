package com.rave.rickandmortyv2.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: RepositoryImpl
) : ViewModel() {
    private var _characterList : MutableStateFlow<PagingData<Character>?> = MutableStateFlow(null)
    val characterList = _characterList.asStateFlow()

    init{
        getAllCharacters()
    }

    private fun getAllCharacters() = viewModelScope.launch{
        repo.getCharacters().collect{ list ->
            _characterList.value = list
        }
    }
}