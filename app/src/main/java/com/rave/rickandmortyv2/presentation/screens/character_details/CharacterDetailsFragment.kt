package com.rave.rickandmortyv2.presentation.screens.character_details

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
import com.lib_data.domain.models.CharacterDetails
import com.lib_data.resources.Resource
import com.rave.rickandmortyv2.databinding.FragmentCharacterDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment: Fragment() {
    private var _binding : FragmentCharacterDetailsBinding? = null
    val binding : FragmentCharacterDetailsBinding get() = _binding!!
    private val viewModel by viewModels<CharacterDetailsViewModel>()
    private val args by navArgs<CharacterDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCharacterDetailsBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initCharacterDetails()
        initListeners()
    }

    private fun initViews() = with(binding){
        collectLatestLifecycleFlow(viewModel.characterDetails){ details ->
            when (details) {
                is Resource.Idle -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    displayCharacterDetails(details.data)
                    val id = details.data.location.url.substringAfter("n/").toInt()
                    llOriginLocation.setOnClickListener {
                        navigateToLocationDetails(id)
                    }
                }
            }
        }
    }

    private fun initCharacterDetails() = with(binding){
        val id = args.id
        viewModel.getCharacterById(id)
    }

    private fun displayCharacterDetails(details: CharacterDetails) = with(binding){
        tvName.text = details.name
        ivImage.load(details.image)
        val status = "Status: ${details.status}"
        tvStatus.text = status
        tvSpecies.text = details.species
        tvGender.text = details.gender
        tvOrigin.text = details.origin.name
        tvLocation.text = details.location.name
        val episodesNumber = "${details.episode.count()} total episodes"
        tvEpisodes.text = episodesNumber
    }

    private fun initListeners() = with(binding){
        btnBack.setOnClickListener {
            navigateToDashboard()
        }
        llEpisode.setOnClickListener{
            navigateToCharacterEpisodeList(args.id)
        }
    }

    private fun navigateToLocationDetails(id: Int){
        findNavController().navigate(
            CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToLocationDetailsFragment(id)
        )
    }
    private fun navigateToDashboard(){
        findNavController().navigate(
            CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToDashboardFragment()
        )
    }

    private fun navigateToCharacterEpisodeList(id: Int) {
        findNavController().navigate(
            CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToCharacterEpisodesFragment(id)
        )
    }
}