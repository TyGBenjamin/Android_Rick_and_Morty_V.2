package com.rave.rickandmortyv2.screens.episodes_list

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
import com.rave.rickandmortyv2.databinding.FragmentEpisodesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesListFragment : Fragment() {
    private var _binding: FragmentEpisodesListBinding? = null
    private val binding: FragmentEpisodesListBinding get() = _binding!!
    private val viewModel by viewModels<EpisodesListViewModel>()
    private val adapter by lazy { EpisodeListAdapter(@FragmentEpisodesListBinding::handleThumbnailClick) }
    private val safeArgs: EpisodesListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEpisodesListBinding.inflate(inflater, container, false).also {
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
                        tvCharName.text = "${character.data.name}'s Episodes"
                        rvEpisodes.adapter = adapter.apply { addItems(character.data.episode) }
                    }
                }
            }
        }
    }

    private fun handleThumbnailClick(episodeUrl: String) {
        navigateToCharacterAppearances(GET_ID_BY_URL(episodeUrl))
    }

    private fun navigateToCharacterAppearances(episodeId: String) {
        val action =
            EpisodesListFragmentDirections.actionCharacterEpisodesFragmentToCharacterAppearancesFragment(
                episodeId
            )
        findNavController().navigate(action)
    }
}