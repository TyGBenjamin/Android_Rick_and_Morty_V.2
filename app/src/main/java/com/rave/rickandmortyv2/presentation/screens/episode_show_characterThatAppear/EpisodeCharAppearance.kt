package com.rave.rickandmortyv2.presentation.screens.char_show_EpisodesCharAppears

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.utils.Constants
import com.example.lib_data.utils.Constants.getIdFromUrl
import com.example.lib_data.utils.Resource
import com.rave.rickandmortyv2.adapters.AppearanceAdapter
import com.rave.rickandmortyv2.adapters.EpisodeAdapter
import com.rave.rickandmortyv2.adapters.LocationAdapter
import com.rave.rickandmortyv2.databinding.EpisodeListBinding
import com.rave.rickandmortyv2.databinding.FragmentCharDetailsBinding
import com.rave.rickandmortyv2.databinding.FragmentCharacterEpisodeListBinding
import com.rave.rickandmortyv2.databinding.FragmentEpisodeCharAppearanceBinding
import com.rave.rickandmortyv2.presentation.screens.char_details.CharDetailsArgs
import com.rave.rickandmortyv2.presentation.screens.char_details.CharDetailsDirections
import com.rave.rickandmortyv2.presentation.screens.char_details.CharDetailsViewModel
import com.rave.rickandmortyv2.presentation.screens.location_details.LocationDetailsDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class EpisodeCharAppearance : Fragment() {

    private var _binding: FragmentEpisodeCharAppearanceBinding? = null
    private val binding: FragmentEpisodeCharAppearanceBinding get() = _binding!!
    private val viewModel by viewModels<EpisodeCharAppearanceViewModel>()
    private val safeArgs: EpisodeCharAppearanceArgs by navArgs()
    private val appearanceAdapter by lazy { AppearanceAdapter(::navigateToAppearance) }
    private var trackList: MutableList<com.example.lib_data.domain.models.Character> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEpisodeCharAppearanceBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() = with(binding) {
        lifecycleScope.launch {
            viewModel.setEpisode(safeArgs.episodeId)
            viewModel.episode.collectLatest { episode ->
                when (episode) {

                    is Resource.Error -> Log.d(
                        TAG,
                        "initViewsLOG - Error: ${episode.message}"
                    )
                    is Resource.Loading -> Log.d(TAG, "initViews: Loading....")
                    is Resource.Success -> {

                        println("initViews - Success the following is  ${episode.data}")
                        tvEpisodeName.text = episode.data.name
                        airDate.text = episode.data.air_date
                        setCharUrl(episode.data.characters)

                    }
                    null -> Log.d(TAG, "THERES SOME NULLS HERE")
                }
            }
        }

        lifecycleScope.launch {
            viewModel.char.collectLatest { char ->
                rvAppearance.adapter = appearanceAdapter
                when (char) {
                    is Resource.Error -> Log.d(
                        TAG,
                        "initViewsLOG - Error: ${char.message}"
                    )
                    is Resource.Loading -> Log.d(TAG, "initViews: Loading....")
                    is Resource.Success -> {
//                        println("initViews - Success the following is  ${episode.data}")
                        trackList.add(char.data)
                        println("Episode char list $trackList")
                        appearanceAdapter.addItems(trackList)
//                        episodeAdapter.EpisodeListViewHolder().


                    }
                    null -> Log.d(TAG, "THERES SOME NULLS HERE")
                }
            }

        }


    }

    private fun navigateToAppearance(episodeId: Int) {
        val action =
            CharacterEpisodeListDirections.actionCharacterEpisodeListToEpisodeCharAppearance(
                episodeId
            )
        findNavController().navigate(action)
    }

    private fun setCharUrl(charList: List<String>) {
        for (character in charList) {
            viewModel.setChar(getIdFromUrl(character))
        }

    }

    companion object {
        const val TAG = "FragmentLogger"
    }

}