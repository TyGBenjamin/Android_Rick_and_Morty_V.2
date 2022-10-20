package com.rave.rickandmortyv2.presentation.screens.char_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.CharWrapper
import com.example.lib_data.domain.models.Data
import com.example.lib_data.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.lib_data.domain.models.Character

@HiltViewModel
class CharDetailsViewModel @Inject constructor(
    private val repo: RepositoryImpl
): ViewModel() {
    private val _char: MutableStateFlow<Resource<Character>?> = MutableStateFlow(Resource.Loading)
    val char = _char.asStateFlow()

    fun setChar(charId: Int) {
        viewModelScope.launch {
            _char.value = repo.getCharacterById(charId)
        }
    }


}