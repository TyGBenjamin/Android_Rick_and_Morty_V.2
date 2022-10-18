package com.rave.rickandmortyv2.presentation.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Data
import com.example.lib_data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo : RepositoryImpl
): ViewModel(){

    private val _Char: MutableStateFlow<Resource<Data>> = MutableStateFlow(Resource.Loading)

    val Char = _Char.asStateFlow()

    init {
        getCharacterList()
    }


    fun getCharacterList(){
        viewModelScope.launch {
            _Char.value = repo.getCharacterList()
        }
    }

//    fun getEpisodeName(episodeId: String): String{
//        var name = "error"
//        viewModelScope.launch {
//            when(val episodeName = repo.getEpisodeById(episodeId.toInt())
//
//
//
//        }
//    }
}