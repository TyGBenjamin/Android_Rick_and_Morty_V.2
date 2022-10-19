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
import androidx.navigation.fragment.navArgs
import com.alecbrando.musicplayer.utils.collectLatestLifecycleFlow
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.domain.models.LocationDetails
import com.lib_data.resources.Resource
import com.rave.rickandmortyv2.databinding.FragmentLocationDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailsFragment: Fragment() {
    private var _binding: FragmentLocationDetailsBinding? = null
    val binding : FragmentLocationDetailsBinding get() = _binding!!
    private val viewModel by viewModels<LocationDetailsViewModel>()
    private val locationDetailsAdapter by lazy { LocationDetailsAdapter() }
    private val args by navArgs<LocationDetailsFragmentArgs>()
    private var residentList: List<String> = emptyList()
    private var characterList: MutableList<CharacterDetails> = mutableListOf()

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
        initLocationDetails()
        initListeners()
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
//                    getResidentId(details.data.residents)
                    setResidentList(details.data.residents)
//                    residentList = details.data.residents


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
                    characterList.add(characters.data)
                    locationDetailsAdapter.addLocationDetails(characterList)
                }
            }
        }

    }

    private fun initLocationDetails() {
        viewModel.getLocationDetailsById(args.id)
    }

    private fun setResidentList(details: List<String>){
        residentList = details
        for(i in residentList){
            Log.d(TAG, "initViews: resident list $i")
        }
    }
    
    private fun getResidentId(details: List<String>){
        for(i in details.indices){
            var url = details[i].subSequence(42, details[i].lastIndex+1) as String
            var id = url.toInt()
            viewModel.getCharacterById(id)
        }
    }

    private fun initListeners() {

    }

}