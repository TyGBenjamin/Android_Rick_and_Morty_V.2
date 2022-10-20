package com.rave.rickandmortyv2.screens.location_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Location
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailsViewModel @Inject constructor(
    private val repo: RepositoryImpl
): ViewModel() {
    private val _location: MutableStateFlow<Resource<Location>> = MutableStateFlow(Resource.Loading)
    var location = _location.asStateFlow()

    private val _resident: MutableStateFlow<Resource<Character>> = MutableStateFlow(Resource.Loading)
    var resident = _resident.asStateFlow()

    fun setLocation(locationId: Int) = viewModelScope.launch {
        _location.value = repo.getLocationById(locationId)
    }

    fun setResident(residentId: Int) = viewModelScope.launch {
        _resident.value = repo.getCharacterById(residentId)
    }
}