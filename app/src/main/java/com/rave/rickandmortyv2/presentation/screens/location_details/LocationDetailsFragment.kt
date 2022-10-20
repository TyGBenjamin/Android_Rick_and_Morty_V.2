package com.rave.rickandmortyv2.presentation.screens.location_details

import android.content.ContentValues.TAG
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alecbrando.musicplayer.utils.collectLatestLifecycleFlow
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.LocationDetails
import com.lib_data.resources.Resource
import com.rave.rickandmortyv2.databinding.FragmentLocationDetailsBinding
import com.rave.rickandmortyv2.presentation.screens.character_details.CharacterDetailsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailsFragment: Fragment() {
    private var _binding: FragmentLocationDetailsBinding? = null
    val binding : FragmentLocationDetailsBinding get() = _binding!!
    private val viewModel by viewModels<LocationDetailsViewModel>()
    private val locationDetailsAdapter by lazy { LocationDetailsAdapter(::navigateToCharacterDetails) }
    private val args by navArgs<LocationDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentLocationDetailsBinding.inflate(inflater,container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initGetLocationDetailsIdFromArgs()
    }

    private fun initViews() = with(binding){
        collectLatestLifecycleFlow(viewModel.locationDetails){ details ->
            when (details) {
                is Resource.Idle -> {}
                is Resource.Error -> {
                    Log.d(TAG, "initViews: This is the error: ${details.message}")}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    tvLocationName.text = details.data.name
                    val type = "Location Type: ${details.data.type}"
                    tvLocationType.text = type
                    val dimension = "Dimension: ${details.data.dimension}"
                    tvLocationDimension.text = dimension
                    getResidentById(details.data.residents)
                }
            }
        }

        collectLatestLifecycleFlow(viewModel.characterList){ characters ->
            locationDetailsRecyclerView.adapter = locationDetailsAdapter
            when (characters) {
                is Resource.Idle -> {}
                is Resource.Error -> {
                    Log.d(TAG, "initViews: This is the error: ${characters.message}")}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    locationDetailsAdapter.addLocationDetails(characters.data)
                }
            }
        }
    }

    private fun initListeners() = with(binding){
        binding.btnBack.setOnClickListener {
            navigateToDashboard()
        }
    }

    private fun initGetLocationDetailsIdFromArgs() {
        viewModel.getLocationDetailsById(args.id)
    }

    private fun getResidentById(details: List<String>){
        for(i in details.indices){
            var url = details[i].subSequence(42, details[i].lastIndex+1) as String
            var id = url.toInt()
            viewModel.getCharacterById(id)
        }
    }

    private fun navigateToCharacterDetails(id: Int){
        findNavController().navigate(
            LocationDetailsFragmentDirections.actionLocationDetailsFragmentToCharacterDetailsFragment(id)
        )
    }

    private fun navigateToDashboard(){
        findNavController().navigate(
            LocationDetailsFragmentDirections.actionLocationDetailsFragmentToDashboardFragment()
        )
    }
}