package com.rave.rickandmortyv2.presentation.screen.location_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Character
import com.example.lib_data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.lib_data.domain.models.LocationDetails
import kotlinx.coroutines.launch

@HiltViewModel
class LocationDetailsViewModel @Inject constructor(
    private val repo: RepositoryImpl
): ViewModel(){
    private val _charDetails: MutableStateFlow<Resource<Character>> = MutableStateFlow(Resource.Loading)
    val char = _charDetails.asStateFlow()

    private val _location: MutableStateFlow<Resource<LocationDetails>> = MutableStateFlow(Resource.Loading)
    val location = _location.asStateFlow()



    fun getCharacterDetails(charId: String){
        viewModelScope.launch {
            _charDetails.value = repo.getCharactersById(charId)
        }
    }

    fun getLocationDetails(locationId: String){
        viewModelScope.launch {
            _location.value = repo.getLocationById(locationId)
        }
    }


}