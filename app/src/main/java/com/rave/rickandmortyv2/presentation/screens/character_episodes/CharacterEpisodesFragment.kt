package com.rave.rickandmortyv2.presentation.screens.character_episodes

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.alecbrando.musicplayer.utils.collectLatestLifecycleFlow
import com.lib_data.domain.models.EpisodeDetails
import com.lib_data.resources.Resource
import com.rave.rickandmortyv2.databinding.FragmentCharacterEpisodesBinding
import com.rave.rickandmortyv2.presentation.screens.dashboard.DashboardFragmentDirections
import com.rave.rickandmortyv2.presentation.screens.location_details.LocationDetailsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterEpisodesFragment: Fragment() {
    private var _binding : FragmentCharacterEpisodesBinding? = null
    private val binding : FragmentCharacterEpisodesBinding get() = _binding!!
    private val viewModel by viewModels<CharacterEpisodesViewModel>()
    private val characterEpisodeAdaptor by lazy { CharacterEpisodesAdapter(::navigateToEpisodeCharacterAppearance) }
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
        characterEpisodesRecyclerView.adapter = characterEpisodeAdaptor
        collectLatestLifecycleFlow(viewModel.characterDetails){ character ->
            when (character) {
                is Resource.Idle -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    ivImage.load(character.data.image)
                    val name = "${character.data.name}'s Episodes"
                    tvName.text = name
                    getEpisodeDetailsById(character.data.episode)
                }
            }
        }
        collectLatestLifecycleFlow(viewModel.episodeList){ episodes ->
            when (episodes) {
                is Resource.Idle -> {}
                is Resource.Error -> {
                    Log.d(TAG, "initViews: something is wrong")}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    characterEpisodeAdaptor.addAllEpisodes(episodes.data)
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

    private fun getEpisodeDetailsById(details: List<String>){
        for(i in details.indices){
            var id = details[i].substringAfterLast('/').toInt()
            viewModel.getEpisodesById(id)
        }
    }

    private fun navigateToEpisodeCharacterAppearance(id: Int){
        findNavController().navigate(
            CharacterEpisodesFragmentDirections.actionCharacterEpisodesFragmentToEpisodeCharacterAppearanceFragment(id)
        )
    }
}