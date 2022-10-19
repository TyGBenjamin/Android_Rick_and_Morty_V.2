package com.rave.rickandmortyv2.screens.location_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lib_data.domain.util.Constants.GET_ID_BY_URL
import com.example.lib_data.domain.util.Resource
import com.rave.rickandmortyv2.databinding.FragmentLocationDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationDetailsFragment : Fragment() {
    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding: FragmentLocationDetailsBinding get() = _binding!!
    private val viewModel by viewModels<LocationDetailsViewModel>()
    private val adapter by lazy { LocationDetailsAdapter(@FragmentLocationDetailsBinding ::handleThumbnailClick) }
    private val safeArgs: LocationDetailsFragmentArgs by navArgs()

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
        lifecycleScope.launch {
            viewModel.setLocation(safeArgs.locationId.toInt())
            viewModel.location.collectLatest { location ->
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
                        rvResidents.adapter = adapter.apply { addItems(location.data.residents) }
                    }
                }
            }
        }
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