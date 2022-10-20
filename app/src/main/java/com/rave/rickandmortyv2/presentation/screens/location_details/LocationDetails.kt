package com.rave.rickandmortyv2.presentation.screens.location_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lib_data.utils.Resource
import com.rave.rickandmortyv2.adapters.LocationAdapter
import com.rave.rickandmortyv2.databinding.FragmentLocationDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.lib_data.domain.models.Character
import com.example.lib_data.utils.Constants
import com.rave.rickandmortyv2.presentation.screens.char_show_EpisodesCharAppears.CharacterEpisodeList.Companion.TAG

@AndroidEntryPoint
class LocationDetails : Fragment() {

    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding: FragmentLocationDetailsBinding get() = _binding!!
    private val viewModel by viewModels<LocationDetailsViewModel>()
    private val locationAdapter by lazy { LocationAdapter(::navigateToChar) }
    private val safeArgs: LocationDetailsArgs by navArgs()
    private val charList: MutableList<Character> = mutableListOf()
//    private val charAdapter: CharAdapter by lazy { CharAdapter(::navigateToDetails) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentLocationDetailsBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
//        rvView.adapter = charAdapter
        lifecycleScope.launch {
            viewModel.setLocation(safeArgs.locationId)
            viewModel.locate.collectLatest { viewState ->
                when (viewState) {
                    is Resource.Error -> {
                        Log.d(TAG, "ERROR: ${viewState.message}")
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "Loading...")
                    }
                    is Resource.Success -> rvLocation.adapter = locationAdapter.apply {

                        Log.d(TAG, "IM OVER HERE")
                        tvDimension.text = viewState.data.dimension
                        tvEpisodeName.text = viewState.data.name
                        airDate.text = viewState.data.type
                        setLocationUrl(viewState.data.residents)
                    }

                }
            }
        }

        lifecycleScope.launch {
            viewModel.char.collectLatest { char ->
                rvLocation.adapter = locationAdapter
                when(char) {
                    is Resource.Error -> {}
                    Resource.Loading -> {}
                    is Resource.Success -> {
                        charList.add(char.data)
                        locationAdapter.addResidents(charList)
                    }
                }
            }
        }
    }


    private fun navigateToChar(charId: Int) {
        val action = LocationDetailsDirections.actionLocationDetailsToCharDetails(charId)
        findNavController().navigate(action)
    }

    private fun setLocationUrl(resList: List<String>) {
        for (resident in resList) {
            viewModel.getCharacterDetails(Constants.getIdFromUrl(resident))
        }

    }

}


