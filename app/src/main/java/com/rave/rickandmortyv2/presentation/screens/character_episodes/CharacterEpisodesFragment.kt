package com.rave.rickandmortyv2.presentation.screens.character_episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.alecbrando.musicplayer.utils.collectLatestLifecycleFlow
import com.lib_data.resources.Resource
import com.rave.rickandmortyv2.databinding.FragmentCharacterDetailsBinding
import com.rave.rickandmortyv2.databinding.FragmentCharacterEpisodesBinding
import com.rave.rickandmortyv2.presentation.screens.character_details.CharacterDetailsFragmentArgs
import com.rave.rickandmortyv2.presentation.screens.character_details.CharacterDetailsViewModel
import com.rave.rickandmortyv2.presentation.screens.location_details.LocationDetailsFragmentArgs
import com.rave.rickandmortyv2.presentation.screens.location_details.LocationDetailsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterEpisodesFragment: Fragment() {
    private var _binding : FragmentCharacterEpisodesBinding? = null
    private val binding : FragmentCharacterEpisodesBinding get() = _binding!!
    private val viewModel by viewModels<CharacterEpisodesViewModel>()
    private val args by navArgs<CharacterEpisodesFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCharacterEpisodesBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initCharacterDetails()
        initListeners()
    }

    private fun initViews() = with(binding){
        collectLatestLifecycleFlow(viewModel.characterDetails){ character ->
            when (character) {
                is Resource.Idle -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    ivImage.load(character.data.image)
                    val name = "${character.data.name}'s Episodes"
                    tvName.text = name
                }
            }
        }
        collectLatestLifecycleFlow(viewModel.episodeList){ episodes ->
            when (episodes) {
                is Resource.Idle -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {


                }
            }
        }
    }

    private fun initListeners() = with(binding){
        btnBack.setOnClickListener{
            navigateToDashboard()
        }
    }

    private fun initCharacterDetails() {
        val id = args.id
        viewModel.getCharacterById(id)
    }

    private fun navigateToDashboard() {
        findNavController().navigate(
            CharacterEpisodesFragmentDirections.actionCharacterEpisodesFragmentToDashboardFragment())
    }
}