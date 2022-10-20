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
import com.example.lib_data.utils.Constants
import com.example.lib_data.utils.Resource
import com.rave.rickandmortyv2.databinding.EpisodeListBinding
import com.rave.rickandmortyv2.databinding.FragmentCharDetailsBinding
import com.rave.rickandmortyv2.databinding.FragmentCharacterEpisodeListBinding
import com.rave.rickandmortyv2.presentation.screens.char_details.CharDetailsArgs
import com.rave.rickandmortyv2.presentation.screens.char_details.CharDetailsDirections
import com.rave.rickandmortyv2.presentation.screens.char_details.CharDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharacterEpisodeList : Fragment() {

    private var _binding: FragmentCharacterEpisodeListBinding? = null
    private val binding: FragmentCharacterEpisodeListBinding get() = _binding!!
    private val viewModel by viewModels<CharacterEpisodeListViewModel>()
    private val safeArgs: CharacterEpisodeListArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCharacterEpisodeListBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() = with(binding) {
        lifecycleScope.launch {
            viewModel.setChar(safeArgs.charID)
            viewModel.char.collectLatest { episode ->
                when (episode) {

                    is Resource.Error -> Log.d(
                        TAG,
                        "initViewsLOG - Error: ${episode.message}"
                    )
                    is Resource.Loading -> Log.d(TAG, "initViews: Loading....")
                    is Resource.Success -> {

                        println("initViews - Success the following is  ${episode.data}")
                        tvTitle.text = episode.data.name
                        imageView3.load(episode.data.image)

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