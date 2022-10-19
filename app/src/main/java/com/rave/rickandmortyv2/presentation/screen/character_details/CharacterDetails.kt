package com.rave.rickandmortyv2.presentation.screen.character_details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.lib_data.util.Resource
import com.rave.rickandmortyv2.R
import com.rave.rickandmortyv2.databinding.FragmentCharacterDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharacterDetails : Fragment() {
    private var _binding: FragmentCharacterDetailsBinding?= null
    private val binding : FragmentCharacterDetailsBinding get() = _binding!!
    private  val viewModel by viewModels<CharacterDetailsViewModel>()
    private val args by navArgs<CharacterDetailsArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =  FragmentCharacterDetailsBinding.inflate(inflater, container, false).also { _binding = it}.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacterDetails(args.characterId)

        lifecycleScope.launch{
            viewModel.char.collectLatest { charDetails->
                when(charDetails){
                    is Resource.Error -> {}
                    Resource.Loading -> {}
                    is Resource.Success -> {
                        Log.d("success", "onViewCreated: $charDetails")
                        with(binding){
                            tvCharLocation.text = charDetails.data.location.name

                            tvCharName.text = charDetails.data.name
                            tvCharStat.text = charDetails.data.status
                            imageView2.load(charDetails.data.image)
                            tvEpNum.text = charDetails.data.episode.size.toString()
                            tvOrigName.text = charDetails.data.origin.name
                            tvSpecies.text = charDetails.data.species
                            tvGender.text = charDetails.data.gender


                        }
                    }
                    is Resource.Error -> TODO()
                    Resource.Loading -> TODO()
                    is Resource.Success -> TODO()
                }

            }



        }


    }



}