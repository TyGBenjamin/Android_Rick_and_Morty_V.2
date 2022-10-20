package com.rave.rickandmortyv2.screens.character_appearances

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lib_data.domain.util.Constants.GET_ID_BY_URL
import com.example.lib_data.domain.util.Resource
import com.rave.rickandmortyv2.databinding.FragmentCharacterAppearancesBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.lib_data.domain.models.Character
import com.example.lib_data.domain.util.collectLatestLifecycleFlow

@AndroidEntryPoint
class CharacterAppearancesFragment : Fragment() {
    private var _binding: FragmentCharacterAppearancesBinding? = null
    private val binding: FragmentCharacterAppearancesBinding get() = _binding!!
    private val viewModel by viewModels<CharacterAppearancesViewModel>()
    private val adapter by lazy { CharacterAppearancesAdapter(@CharacterAppearancesFragment ::handleThumbnailClick) }
    private val safeArgs: CharacterAppearancesFragmentArgs by navArgs()
    private var characterList: MutableList<Character> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCharacterAppearancesBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.setEpisode(safeArgs.episodeId.toInt())
    }

    private fun initViews() = with(binding) {
        collectLatestLifecycleFlow(viewModel.episode) { episode ->
            when (episode) {
                is Resource.Error -> {}
                Resource.Loading -> {}
                is Resource.Success -> {
                    tb.tvEpiName.text = episode.data.name
                    tb.tvEpiAirDate.text = episode.data.date
                    tb.tvEpiEpisode.text = episode.data.episode
                    setCharacters(episode.data.characters)
                }
            }
        }
        collectLatestLifecycleFlow(viewModel.character) { character ->
            rvAppearances.adapter = adapter
            when (character) {
                is Resource.Error -> {}
                Resource.Loading -> {}
                is Resource.Success -> {
                    characterList.add(character.data)
                    adapter.addCharacters(characterList)
                }
            }
        }
    }

    private fun setCharacters(characterUrls: List<String>) {
        for (url in characterUrls) viewModel.setCharacter(GET_ID_BY_URL(url).toInt())
    }

    private fun handleThumbnailClick(charUrl: String) = navigateToCharDetails(GET_ID_BY_URL(charUrl))


    private fun navigateToCharDetails(charId: String) {
        val action =
            CharacterAppearancesFragmentDirections.actionCharacterAppearancesFragmentToCharacterDetailsFragment(
                charId
            )
        findNavController().navigate(action)
    }
}