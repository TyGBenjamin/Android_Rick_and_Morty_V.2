package com.rave.rickandmortyv2.screens.character_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val repo: RepositoryImpl
): ViewModel() {
    private val _char: MutableStateFlow<Resource<Character>> = MutableStateFlow(Resource.Loading)
    val char = _char.asStateFlow()

    fun setCharacter(id: Int) = viewModelScope.launch {
        _char.value = repo.getCharacterById(id)
    }
}