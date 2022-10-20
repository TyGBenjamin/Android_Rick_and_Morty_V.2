package com.rave.rickandmortyv2.presentation.screens.location_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.LocationDetails
import com.example.lib_data.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailsViewModel @Inject constructor(
    private val repo: RepositoryImpl
): ViewModel() {
    private val _locate: MutableStateFlow<Resource<LocationDetails>?> = MutableStateFlow(Resource.Loading)
    val locate = _locate.asStateFlow()

    private val _char: MutableStateFlow<Resource<com.example.lib_data.domain.models.Character>> =
        MutableStateFlow(Resource.Loading)
    val char = _char.asStateFlow()

    fun setLocation(Id: Int) {
        viewModelScope.launch {
            _locate.value = repo.getLocationById(Id)
        }
    }

    fun getCharacterDetails(charId: Int){
        viewModelScope.launch {
            _char.value = repo.getCharacterById(charId)
        }
    }
}