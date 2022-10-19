package com.rave.rickandmortyv2.screens.character_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.lib_data.domain.util.Constants.GET_ID_BY_URL
import com.example.lib_data.domain.util.Resource
import com.rave.rickandmortyv2.databinding.FragmentCharacterDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {
    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding: FragmentCharacterDetailsBinding get() = _binding!!
    private val viewModel by viewModels<CharacterDetailsViewModel>()
    private val safeArgs: CharacterDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCharacterDetailsBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch {
            viewModel.setCharacter(safeArgs.charId.toInt())
            viewModel.char.collectLatest { character ->
                when (character) {
                    is Resource.Error -> {}
                    Resource.Loading -> {}
                    is Resource.Success -> {
                        ivCharImg.load(character.data.image)

                        tvCharName.text = character.data.name
                        tvCharStatus.text = "Status: ${character.data.status}"
                        tvCharSpecies.text = "Species: ${character.data.species}"
                        tvCharGender.text = "Gender: ${character.data.gender}"

                        btnToOrigin.text = character.data.origin.name
                        btnToLocation.text = character.data.location.name
                        btnToEpisode.text =
                            character.data.episode.size.toString() + " total episodes"

                        btnToLocation.setOnClickListener {
                            if(character.data.location.url != "") {
                                navigateToLocationDetails(GET_ID_BY_URL(character.data.location.url!!))
                            }
                        }
                        btnToOrigin.setOnClickListener {
                            if(character.data.origin.url != "") {
                                navigateToLocationDetails(GET_ID_BY_URL(character.data.origin.url!!))
                            }
                        }
                        btnToEpisode.setOnClickListener {
                            if(character.data.episode.isNotEmpty()) {
                                navigateToCharEpisodes(character.data.id.toString())
                            }
                        }
                    }
                }
            }
        }
    }


    private fun navigateToCharEpisodes(charId: String) {
        val action =
            CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToCharacterEpisodesFragment(
                charId
            )
        findNavController().navigate(action)
    }

    private fun navigateToLocationDetails(locationId: String) {
        val action =
            CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToLocationDetailsFragment(
                locationId
            )
        findNavController().navigate(action)
    }
}