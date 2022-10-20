package com.rave.rickandmortyv2.screens.location_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.lib_data.domain.models.Character
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lib_data.domain.util.Constants.GET_ID_BY_URL
import com.example.lib_data.domain.util.Resource
import com.example.lib_data.domain.util.collectLatestLifecycleFlow
import com.rave.rickandmortyv2.databinding.FragmentLocationDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class LocationDetailsFragment : Fragment() {
    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding: FragmentLocationDetailsBinding get() = _binding!!
    private val viewModel by viewModels<LocationDetailsViewModel>()
    private val adapter by lazy { LocationDetailsAdapter(@FragmentLocationDetailsBinding ::handleThumbnailClick) }
    private val safeArgs: LocationDetailsFragmentArgs by navArgs()
    private val residents: MutableList<Character> = mutableListOf()

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
        viewModel.setLocation(safeArgs.locationId.toInt())
        collectLatestLifecycleFlow(viewModel.location) { location ->
            when (location) {
                is Resource.Error -> {
                    Toast.makeText(context, "Error: Unable to Fetch Data", Toast.LENGTH_SHORT)
                        .show()
                }
                Resource.Loading -> {}
                is Resource.Success -> {
                    tvLocName.text = location.data.name
                    tvLocType.text = location.data.type
                    tvLocDimension.text = location.data.dimension
                    setResidents(location.data.residents)
                }
            }
        }

        collectLatestLifecycleFlow(viewModel.resident) { resident ->
            rvResidents.adapter = adapter
            when(resident) {
                is Resource.Error -> {}
                Resource.Loading -> {}
                is Resource.Success -> {
                    residents.add(resident.data)
                    adapter.addResidents(residents)
                }
            }
        }
    }

    private fun setResidents(residentUrls: List<String>) {
        for(url in residentUrls) viewModel.setResident(GET_ID_BY_URL(url).toInt())
    }

    private fun handleThumbnailClick(charUrl: String) {
        navigateToCharDetails(GET_ID_BY_URL(charUrl))
    }

    private fun navigateToCharDetails(charId: String) {
        val action =
            LocationDetailsFragmentDirections.actionLocationDetailsFragmentToCharacterDetailsFragment(
                charId
            )
        findNavController().navigate(action)
    }
}