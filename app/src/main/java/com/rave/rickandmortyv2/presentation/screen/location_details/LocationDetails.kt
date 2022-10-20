package com.rave.rickandmortyv2.presentation.screen.location_details

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.util.Constants
import com.example.lib_data.util.Resource
import com.rave.rickandmortyv2.R
import com.rave.rickandmortyv2.databinding.FragmentCharacterEpisodeBinding
import com.rave.rickandmortyv2.databinding.FragmentLocationDetailsBinding
import com.rave.rickandmortyv2.presentation.screen.character_details.CharacterDetailsArgs
import com.rave.rickandmortyv2.presentation.screen.character_episode.CharacterEpisodeAdapter
import com.rave.rickandmortyv2.presentation.screen.character_episode.CharacterEpisodeViewModel
import com.rave.rickandmortyv2.presentation.screen.episode_details.EpisodeDetailsDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LocationDetails : Fragment() {
    private var _binding: FragmentLocationDetailsBinding?= null
    private val binding: FragmentLocationDetailsBinding get() = _binding!!
    private val viewModel by viewModels<LocationDetailsViewModel>()
    private val locationAdapter by lazy { LocationDetailsAdapter(@FragmentLocationDetailsBinding::characterId) }
    private val args: LocationDetailsArgs by navArgs()
    private val characterList: MutableList<Character> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentLocationDetailsBinding.inflate(inflater,container,false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding){
        lifecycleScope.launch{
            viewModel.getLocationDetails(args.locationId)
            viewModel.location.collectLatest { location ->
                when (location) {
                    is Resource.Error -> {}
                    Resource.Loading -> {}
                    is Resource.Success -> {
                        tvLocDimen.text = location.data.dimension
                        tvLocType.text = location.data.type
                        tvLocationName.text = location.data.name
                        getCharById(location.data.residents)
                    }
                }

            }

        }

        lifecycleScope.launch{
            viewModel.char.collectLatest { char ->
                rvCharApp.adapter = locationAdapter
                when (char) {
                    is Resource.Error -> char.message
                    is Resource.Loading -> Log.d(ContentValues.TAG, "initViews: Loading....")
                    is Resource.Success ->  {
                        println("IS SET: $char")
                        characterList.add(char.data)
                        locationAdapter.addCharacterDetails(characterList)

                    }


                }

            }
        }
    }



    private fun getCharById(charDetails: List<String>){
        for(i in charDetails.indices){
            val url = Constants.getIdFromUrl(charDetails[i])
            viewModel.getCharacterDetails(url)
        }

    }

    private fun characterId(charId: String){
        navigateToCharDetails(charId)
    }



    private fun navigateToCharDetails(charId: String) {
        val action = LocationDetailsDirections.actionLocationDetailsToCharacterDetails(charId)
        findNavController().navigate(action)
    }


}

