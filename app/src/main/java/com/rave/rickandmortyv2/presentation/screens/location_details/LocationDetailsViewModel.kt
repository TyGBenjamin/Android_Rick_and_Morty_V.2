package com.rave.rickandmortyv2.presentation.screens.location_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.CharacterWrapper
import com.lib_data.domain.models.LocationDetails
import com.lib_data.domain.use_cases.GetAllCharactersUseCase
import com.lib_data.domain.use_cases.GetCharacterByIdUseCase
import com.lib_data.domain.use_cases.GetLocationDetailsByIdUseCase
import com.lib_data.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailsViewModel @Inject constructor(
    private val getLocationDetailsByIdUseCase: GetLocationDetailsByIdUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
): ViewModel() {
    private var _locationDetails : MutableStateFlow<Resource<LocationDetails>> = MutableStateFlow(Resource.Idle)
    val locationDetails = _locationDetails.asStateFlow()
    private var _characterList : MutableStateFlow<Resource<CharacterDetails>> = MutableStateFlow(Resource.Idle)
    val characterList = _characterList.asStateFlow()

    fun getLocationDetailsById(id: Int) = viewModelScope.launch {
        _locationDetails.value = getLocationDetailsByIdUseCase.invoke(id)
    }

    fun getCharacterById(id: Int) = viewModelScope.launch {
        _characterList.value = getCharacterByIdUseCase.invoke(id)
    }



}