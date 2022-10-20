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

@AndroidEntryPoint
class LocationDetails : Fragment() {

    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding: FragmentLocationDetailsBinding get() = _binding!!
    private val viewModel by viewModels<LocationDetailsViewModel>()
    private  val locationAdapter by lazy { LocationAdapter(::navigateToChar) }
    private val safeArgs: LocationDetailsArgs by navArgs()
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
        lifecycleScope.launch{
            viewModel.setLocation(safeArgs.locationId)
            viewModel.locate.collectLatest{ viewState ->
                when (viewState) {
                    is Resource.Error -> {
                        Log.d(TAG, "ERROR: ${viewState.message}")
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "Loading...")
                    }
                    is Resource.Success -> rvLocation.adapter = locationAdapter.apply {
                        addItems(viewState.data.residents)
                        Log.d(TAG, "IM OVER HERE")
                        tvDimension.text = viewState.data.dimension
                        tvLocateName.text = viewState.data.name
                        locateType.text = viewState.data.type
                    }

                }
            }
        }

    }

    private fun navigateToChar(charId: Int) {
        val action = LocationDetailsDirections.actionLocationDetailsToCharDetails(charId)
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "LocationFragmentLogger"
    }
}
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(LocationDetailsViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
