package com.rave.rickandmortyv2.presentation.screens.episode_character_appearance

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
import com.alecbrando.musicplayer.utils.collectLatestLifecycleFlow
import com.lib_data.resources.Resource
import com.rave.rickandmortyv2.databinding.FragmentEpisodeCharacterAppearanceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeCharacterAppearanceFragment: Fragment() {
    private var _binding : FragmentEpisodeCharacterAppearanceBinding? = null
    private val binding : FragmentEpisodeCharacterAppearanceBinding get() = _binding!!
    private val viewModel by viewModels<EpisodeCharacterAppearanceViewModel>()
    private val args by navArgs<EpisodeCharacterAppearanceFragmentArgs>()
    private val episodeCharacterAppearanceAdapter by lazy { EpisodeCharacterAppearanceAdapter(::navigateToCharacterDetail) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEpisodeCharacterAppearanceBinding.inflate(inflater, container, false).also{
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initGetEpisodeById(args.id)
        initListeners()
    }

    private fun initViews() = with(binding){
        episodeCharacterAppearanceRecyclerView.adapter = episodeCharacterAppearanceAdapter
        collectLatestLifecycleFlow(viewModel.episodeDetails){ details ->
            when (details) {
                is Resource.Idle -> {}
                is Resource.Error -> {
                    Log.d(TAG, "initViews: fails: Something goes wrong")}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    tvEpisodeName.text = details.data.name
                    tvEpisodeId.text = details.data.id.toString()
                    val airDate = "Air Date: ${details.data.airDate}"
                    tvAirDate.text = airDate
                    getCharacterById(details.data.characters)
                }
            }
        }
        collectLatestLifecycleFlow(viewModel.characterDetails){ characterDetails ->
            when (characterDetails) {
                is Resource.Idle -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    episodeCharacterAppearanceAdapter.addCharacterDetails(characterDetails.data)
                }
            }
        }
    }

    private fun initListeners() = with(binding){
        btnBack.setOnClickListener{
            navigateToDashboard()
        }
    }

    private fun navigateToDashboard() {
        findNavController().navigate(
            EpisodeCharacterAppearanceFragmentDirections.actionEpisodeCharacterAppearanceFragmentToDashboardFragment()
        )
    }

    private fun initGetEpisodeById(id: Int){
        viewModel.getEpisodeById(id)
    }

    private fun getCharacterById(details: List<String>){
        for(i in details.indices){
            val id = details[i].substringAfterLast('/').toInt()
            viewModel.getCharacterById(id)
        }
    }

    private fun navigateToCharacterDetail(id: Int){
        findNavController().navigate(
            EpisodeCharacterAppearanceFragmentDirections.actionEpisodeCharacterAppearanceFragmentToCharacterDetailsFragment(id))
    }
}