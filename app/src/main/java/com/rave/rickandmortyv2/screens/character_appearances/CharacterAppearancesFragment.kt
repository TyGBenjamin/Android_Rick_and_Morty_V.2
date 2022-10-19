package com.rave.rickandmortyv2.screens.character_appearances

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lib_data.domain.util.Constants.GET_ID_BY_URL
import com.example.lib_data.domain.util.Resource
import com.rave.rickandmortyv2.databinding.FragmentCharacterAppearancesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterAppearancesFragment : Fragment() {
    private var _binding: FragmentCharacterAppearancesBinding? = null
    private val binding: FragmentCharacterAppearancesBinding get() = _binding!!
    private val viewModel by viewModels<CharacterAppearancesViewModel>()
    private val adapter by lazy { CharacterAppearancesAdapter(@CharacterAppearancesFragment ::handleThumbnailClick) }
    private val safeArgs: CharacterAppearancesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCharacterAppearancesBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch {
            viewModel.setEpisode(safeArgs.episodeId.toInt())
            viewModel.episode.collectLatest { episode ->
                when (episode) {
                    is Resource.Error -> {}
                    Resource.Loading -> {}
                    is Resource.Success -> {
                        tb.tvEpiName.text = episode.data.name
                        tb.tvEpiAirDate.text = episode.data.date
                        tb.tvEpiEpisode.text = episode.data.episode
                        rvAppearances.adapter = adapter.apply { addItems(episode.data.characters) }
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
            CharacterAppearancesFragmentDirections.actionCharacterAppearancesFragmentToCharacterDetailsFragment(
                charId
            )
        findNavController().navigate(action)
    }
}