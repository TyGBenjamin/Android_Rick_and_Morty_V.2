package com.rave.rickandmortyv2.presentation.screen.character_episode

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.lib_data.domain.models.Episode
import com.example.lib_data.util.Constants.getIdFromUrl
import com.example.lib_data.util.Resource
import com.rave.rickandmortyv2.databinding.FragmentCharacterEpisodeBinding
import com.rave.rickandmortyv2.presentation.screen.character_details.CharacterDetailsArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharacterEpisode : Fragment() {
    private var _binding: FragmentCharacterEpisodeBinding? = null
    private val binding: FragmentCharacterEpisodeBinding get() = _binding!!
    private val viewModel by viewModels<CharacterEpisodeViewModel>()
    private val charEpAdapter by lazy { CharacterEpisodeAdapter(@FragmentCharacterEpisodeBinding::episodeId) }
    private val args by navArgs<CharacterDetailsArgs>()
    private val episodeList: MutableList<Episode> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCharacterEpisodeBinding.inflate(inflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        viewModel.getCharacterDetails(args.characterId)
        lifecycleScope.launch {
            viewModel.char.collectLatest { viewState ->
                when (viewState) {
                    is Resource.Error -> viewState.message
                    is Resource.Loading -> Log.d(ContentValues.TAG, "initViews: Loading....")
                    is Resource.Success ->  {
                        tvCharNameEp.text = viewState.data.name
                        ivCharEp.load(viewState.data.image)
                        getEpisodeById(viewState.data.episode)

                    }


                }

            }

        }
        lifecycleScope.launch{
            viewModel.episode.collectLatest { episode ->
                rvCharEp.adapter = charEpAdapter
                when (episode) {
                    is Resource.Error -> episode.message
                    is Resource.Loading -> Log.d(ContentValues.TAG, "initViews: Loading....")
                    is Resource.Success ->  {
                        println("is set: $episode")
                        episodeList.add(episode.data)
                        charEpAdapter.addEpisodeDetails(episodeList)

                    }


                }

            }
        }

    }

    private fun getEpisodeById(episodeDetails: List<String>){
        for(i in episodeDetails.indices){
            val url = getIdFromUrl(episodeDetails[i])
            viewModel.getEpisodeById(url.toInt())
        }

    }

    private fun episodeId(episode: Int){
        navigateToEpisode(episode)
    }


    private fun navigateToEpisode(episode: Int){
        val action = CharacterEpisodeDirections.actionCharacterEpisodeToEpisodeDetails(episode)
        findNavController().navigate(action)
    }


}