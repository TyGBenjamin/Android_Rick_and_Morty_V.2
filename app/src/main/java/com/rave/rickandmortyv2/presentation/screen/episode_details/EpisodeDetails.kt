package com.rave.rickandmortyv2.presentation.screen.episode_details

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
import com.example.lib_data.util.Constants.getIdFromUrl
import com.example.lib_data.util.Resource
import com.rave.rickandmortyv2.databinding.FragmentEpisodeDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.lib_data.domain.models.Character

@AndroidEntryPoint
class EpisodeDetails : Fragment() {
    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding: FragmentEpisodeDetailsBinding get() = _binding!!
    private  val viewModel by viewModels<EpisodeDetailsViewModel>()
    private val adapter by lazy {EpisodeDetailsAdapter(@EpisodeDetails::characterId)}
    private val safeArgs: EpisodeDetailsArgs by navArgs()
    private val characterList: MutableList<Character> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEpisodeDetailsBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() = with(binding){
        lifecycleScope.launch{
            viewModel.getEpisodeDetails(safeArgs.episodeId)
            viewModel.episode.collectLatest { episode ->
                when (episode) {
                    is Resource.Error -> {}
                    Resource.Loading -> {}
                    is Resource.Success -> {
                        tvAirDate.text = episode.data.air_date
                        tvEpisodeName.text = episode.data.name
                        tvEpiNum.text = episode.data.episode
                        getCharById(episode.data.characters)
                    }
                }

            }

        }

        lifecycleScope.launch{
            viewModel.char.collectLatest { char ->
                rvCharApp.adapter = adapter
                when (char) {
                    is Resource.Error -> char.message
                    is Resource.Loading -> Log.d(ContentValues.TAG, "initViews: Loading....")
                    is Resource.Success ->  {
                        println("IS SET: $char")
                        characterList.add(char.data)
                        adapter.addCharacterDetails(characterList)

                    }


                }

            }
        }
    }



    private fun getCharById(charDetails: List<String>){
        for(i in charDetails.indices){
            val url = getIdFromUrl(charDetails[i])
            viewModel.getCharacterDetails(url)
        }

    }

    private fun characterId(charId: String){
        navigateToCharDetails(charId)
    }



    private fun navigateToCharDetails(charId: String) {
        val action = EpisodeDetailsDirections.actionEpisodeDetailsToCharacterDetails(charId)
        findNavController().navigate(action)
    }


}



