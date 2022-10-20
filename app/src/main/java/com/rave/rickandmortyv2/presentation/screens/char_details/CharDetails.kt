package com.rave.rickandmortyv2.presentation.screens.char_details

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
import coil.load
import com.example.lib_data.utils.Constants.getIdFromUrl
import com.example.lib_data.utils.Resource
import com.rave.rickandmortyv2.databinding.FragmentCharDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharDetails : Fragment() {
    private var _binding: FragmentCharDetailsBinding? = null
    private val binding: FragmentCharDetailsBinding get() = _binding!!
    private val viewModel by viewModels<CharDetailsViewModel>()
    private val safeArgs: CharDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCharDetailsBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
//    private fun initListeners()= with(binding){
//        // TODO
//        lifecycleScope.launch {
//            viewModel.setChar(safeArgs.id)
//            viewModel.char.collectLatest { char ->
//                btnLocation.setOnClickListener {
//                    if (char.data.location.url != null) {
//                        navToLocation(getIdFromUrl(char.data.location.url!!).toString())
//                    }
//                }
//            }
//        }
//    }

    private fun navToLocation(locationId: Int){
        val action = CharDetailsDirections.actionCharDetailsToLocationDetails(locationId)
        findNavController().navigate(action)
    }

    private fun navToEpisode(locationId: Int){
        val action = CharDetailsDirections.actionCharDetailsToCharacterEpisodeList(locationId)
        findNavController().navigate(action)
    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch {
            viewModel.setChar(safeArgs.id)
            viewModel.char.collectLatest { char ->
                when (char) {

                    is Resource.Error -> Log.d(
                        TAG,
                        "initViewsLOG - Error: ${char.message}"
                    )
                    is Resource.Loading -> Log.d(TAG, "initViews: Loading....")
                    is Resource.Success -> {

                        println("initViews - Success the following is  ${char.data}")
                        imageView2.load(char.data.image)
                        tvCharName.text = char.data.name
                        tvCharLocation.text =char.data.location.name
                        tvCharStat.text = "Popularity:  "+ char.data.status
                        tvGender.text = char.data.gender
                        tvSpecies.text = char.data.species
                        tvOrigName.text = char.data.origin.name
                        btnEpisode.text = char.data.episode.size.toString()


                        btnLocation.setOnClickListener{
                            println("Location Button Clicked")
                            if(char.data.location.url?.isNotEmpty()!!){
                                navToLocation(getIdFromUrl(char.data.location.url!!))
                            }
                        }
                        btnOrigin.setOnClickListener{
                            println("Location Button Clicked")
                            if(char.data.origin.url?.isNotEmpty()!!){
                                navToLocation(getIdFromUrl(char.data.origin.url!!))
                            }
                        }

                        btnEpisode.setOnClickListener{
                            println("Episode Button Clicked")
                            if(char.data.episode.isNotEmpty()){
                                navToEpisode(char.data.id)
                            }
                        }

                    }
                    null -> Log.d(TAG, "THERES SOME NULLS HERE")
                }
            }
        }

    }

    companion object {
        const val TAG = "FragmentLogger"
    }
}
